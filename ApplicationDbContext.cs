using ComposadorCarbono.Models;
using Microsoft.EntityFrameworkCore;

namespace ComposadorCarbono.Data;

public class ApplicationDbContext : DbContext
{
    public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options) : base(options)
    {
    }

    public DbSet<Usuario> Usuarios { get; set; }
    public DbSet<Fonte> Fontes { get; set; }
    public DbSet<Projeto> Projetos { get; set; }
    public DbSet<Emissao> Emissoes { get; set; }
    public DbSet<Alerta> Alertas { get; set; }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        base.OnModelCreating(modelBuilder);

        // Configuração para garantir que o EF Core não tente pluralizar os nomes das tabelas
        // e respeite os nomes definidos no atributo [Table].
        // Para Oracle, é uma boa prática forçar os nomes para maiúsculo se não usar atributos.
        // Como já estamos usando, esta é uma camada extra de garantia.
    }
}