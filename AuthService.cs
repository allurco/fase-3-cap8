using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using ComposadorCarbono.Data;
using ComposadorCarbono.DTOs;
using ComposadorCarbono.Models;
using ComposadorCarbono.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;

namespace ComposadorCarbono.Services;

public class AuthService : IAuthService
{
    private readonly ApplicationDbContext _context;
    private readonly IConfiguration _configuration;

    public AuthService(ApplicationDbContext context, IConfiguration configuration)
    {
        _context = context;
        _configuration = configuration;
    }

    public async Task<Usuario> RegisterAsync(RegisterDto registerDto)
    {
        // Verifica se o usuário já existe
        var existingUser = await _context.Usuarios.AnyAsync(u => u.Email == registerDto.Email);
        if (existingUser)
        {
            // Lançar uma exceção ou retornar um resultado específico é uma boa prática.
            throw new InvalidOperationException("O e-mail informado já está em uso.");
        }

        var usuario = new Usuario
        {
            Nome = registerDto.Nome,
            Email = registerDto.Email,
            Senha = BCrypt.Net.BCrypt.HashPassword(registerDto.Senha), // Gera o hash da senha
            Role = registerDto.Role.ToUpper()
        };

        _context.Usuarios.Add(usuario);
        await _context.SaveChangesAsync();
        return usuario;
    }

    public async Task<string?> LoginAsync(LoginDto loginDto)
    {
        var usuario = await _context.Usuarios
            .AsNoTracking()
            .FirstOrDefaultAsync(u => u.Email == loginDto.Email);

        // Verifica se o usuário existe E se a senha fornecida corresponde ao hash armazenado
        if (usuario == null || !BCrypt.Net.BCrypt.Verify(loginDto.Senha, usuario.Senha))
        {
            return null; // Retorna nulo se o usuário não for encontrado ou a senha estiver incorreta
        }
        return GenerateJwtToken(usuario);
    }

    private string GenerateJwtToken(Usuario usuario)
    {
        var tokenHandler = new JwtSecurityTokenHandler();
        var key = Encoding.ASCII.GetBytes(_configuration["Jwt:Secret"]!);
        var tokenDescriptor = new SecurityTokenDescriptor
        {
            Subject = new ClaimsIdentity(new[] { new Claim(ClaimTypes.NameIdentifier, usuario.Id.ToString()), new Claim(ClaimTypes.Email, usuario.Email), new Claim(ClaimTypes.Role, usuario.Role) }),
            Expires = DateTime.UtcNow.AddHours(2),
            SigningCredentials = new SigningCredentials(new SymmetricSecurityKey(key), SecurityAlgorithms.HmacSha256Signature)
        };
        var token = tokenHandler.CreateToken(tokenDescriptor);
        return tokenHandler.WriteToken(token);
    }
}