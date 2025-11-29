using System.Text;
using ComposadorCarbono.Data;
using ComposadorCarbono.Services;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;
using Microsoft.OpenApi.Models;

var builder = WebApplication.CreateBuilder(args);

// --- 1. Registro de Serviços (Injeção de Dependência) ---

// Adiciona os serviços de Controllers para a API
builder.Services.AddControllers();

// Configura o Swagger/OpenAPI para documentação da API
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen(options =>
{
    // Adiciona a definição de segurança para o Bearer Token (JWT)
    options.AddSecurityDefinition("Bearer", new OpenApiSecurityScheme
    {
        In = ParameterLocation.Header,
        Description = "Por favor, insira 'Bearer ' seguido do seu token",
        Name = "Authorization",
        Type = SecuritySchemeType.ApiKey,
        Scheme = "Bearer"
    });
    // Adiciona o requisito de segurança para os endpoints
    options.AddSecurityRequirement(new OpenApiSecurityRequirement
    {
        {
            new OpenApiSecurityScheme { Reference = new OpenApiReference { Type = ReferenceType.SecurityScheme, Id = "Bearer" } },
            Array.Empty<string>()
        }
    });
});

// Configura o ApplicationDbContext para usar o provedor Oracle
var connectionString = builder.Configuration.GetConnectionString("OracleConnection");
builder.Services.AddDbContext<ApplicationDbContext>(options =>
    options.UseOracle(connectionString));

// Registra a camada de serviço para injeção de dependência
builder.Services.AddScoped<IAuthService, AuthService>();

// Configura a Autenticação JWT
var jwtKey = builder.Configuration["Jwt:Secret"]!;
builder.Services.AddAuthentication(options =>
{
    options.DefaultAuthenticateScheme = JwtBearerDefaults.AuthenticationScheme;
    options.DefaultChallengeScheme = JwtBearerDefaults.AuthenticationScheme;
})
.AddJwtBearer(options =>
{
    options.RequireHttpsMetadata = false; // Em produção, considere usar true
    options.SaveToken = true;
    options.TokenValidationParameters = new TokenValidationParameters
    {
        ValidateIssuerSigningKey = true,
        IssuerSigningKey = new SymmetricSecurityKey(Encoding.ASCII.GetBytes(jwtKey)),
        ValidateIssuer = false, // Em produção, valide o emissor do token
        ValidateAudience = false // Em produção, valide a audiência do token
    };
});

var app = builder.Build();

// --- 2. Configuração do Pipeline de Requisições HTTP ---

if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseAuthentication(); // <-- IMPORTANTE: Adiciona o middleware de autenticação
app.UseAuthorization();  // <-- IMPORTANTE: Adiciona o middleware de autorização

app.MapControllers(); // Mapeia as rotas definidas nos seus Controllers

app.Run();