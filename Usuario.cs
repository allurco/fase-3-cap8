using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace ComposadorCarbono.Models;

[Table("USUARIOS")]
public class Usuario
{
    [Key]
    [Column("ID")]
    public int Id { get; set; }

    [Column("NOME")]
    public required string Nome { get; set; }

    [Column("EMAIL")]
    public required string Email { get; set; }

    [Column("SENHA")]
    public required string Senha { get; set; } // Lembre-se de armazenar o hash da senha, não a senha em texto plano.

    [Column("ROLE")]
    public required string Role { get; set; } // "ADMIN" ou "USER"
}