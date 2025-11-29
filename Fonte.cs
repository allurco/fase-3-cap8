using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace ComposadorCarbono.Models;

[Table("FONTES")]
public class Fonte
{
    [Key]
    [Column("ID")]
    public int Id { get; set; }

    [Column("NOME")]
    public required string Nome { get; set; }

    [Column("TIPO")]
    public required string Tipo { get; set; }

    [Column("LOCALIZACAO")]
    public required string Localizacao { get; set; }

    [Column("ID_RESPONSAVEL")]
    public int IdResponsavel { get; set; }
}