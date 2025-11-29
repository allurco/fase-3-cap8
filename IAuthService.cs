using ComposadorCarbono.DTOs;
using ComposadorCarbono.Models;

namespace ComposadorCarbono.Services;

public interface IAuthService
{
    Task<Usuario> RegisterAsync(RegisterDto registerDto);
    Task<string?> LoginAsync(LoginDto loginDto);
}