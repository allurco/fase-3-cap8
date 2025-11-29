using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace ComposadorCarbono.Models;

[Table("ALERTAS")]
public class Alerta
{
    [Key]
    [Column("ID")]
    public int Id { get; set; }

    [Column("MENSAGEM")]
    public required string Mensagem { get; set; }

    [Column("DATA_HORA")]
    public DateTime DataHora { get; set; }

    [Column("LIDO")]
    public bool Lido { get; set; }
}