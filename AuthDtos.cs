using System.ComponentModel.DataAnnotations;

namespace ComposadorCarbono.DTOs;

public record LoginDto([Required] string Email, [Required] string Senha);

public record RegisterDto(
    [Required] string Nome,
    [Required][EmailAddress] string Email,
    [Required] string Senha,
    [Required] string Role
);

public record LoginResponseDto(string Token);