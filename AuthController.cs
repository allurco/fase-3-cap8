using ComposadorCarbono.DTOs;
using ComposadorCarbono.Services;
using Microsoft.AspNetCore.Mvc;

namespace ComposadorCarbono.Controllers;

[ApiController]
[Route("auth")]
public class AuthController : ControllerBase
{
    private readonly IAuthService _authService;

    public AuthController(IAuthService authService)
    {
        _authService = authService;
    }

    [HttpPost("register")]
    [ProducesResponseType(StatusCodes.Status201Created)]
    [ProducesResponseType(StatusCodes.Status400BadRequest)]
    public async Task<IActionResult> Register(RegisterDto registerDto)
    {
        // A lógica de negócio foi movida para o AuthService
        var usuario = await _authService.RegisterAsync(registerDto);
        return CreatedAtAction(nameof(Register), new { id = usuario.Id });
    }

    [HttpPost("login")]
    [ProducesResponseType(typeof(LoginResponseDto), StatusCodes.Status200OK)]
    [ProducesResponseType(StatusCodes.Status401Unauthorized)]
    public async Task<ActionResult<LoginResponseDto>> Login(LoginDto loginDto)
    {
        var token = await _authService.LoginAsync(loginDto);

        if (token == null)
        {
            return Unauthorized("Email ou senha inválidos.");
        }

        return Ok(new LoginResponseDto(token));
    }
}