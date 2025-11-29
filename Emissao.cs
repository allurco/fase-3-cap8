using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace ComposadorCarbono.Models;

[Table("EMISSOES")]
public class Emissao
{
    [Key]
    [Column("ID")]
    public int Id { get; set; }

    [Column("ID_FONTE")]
    public int IdFonte { get; set; }

    [Column("QUANTIDADE_KG_CO2E")]
    public decimal QuantidadeKgCo2e { get; set; }

    [Column("CATEGORIA")]
    public required string Categoria { get; set; }

    [Column("DATA_HORA")]
    public DateTime DataHora { get; set; }

    [Column("METADADOS")]
    public string? Metadados { get; set; } // Mapeado como string, a aplicação trata como JSON
}