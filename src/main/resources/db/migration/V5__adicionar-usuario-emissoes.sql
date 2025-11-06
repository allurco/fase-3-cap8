-- Fix TB_USUARIO: Add missing primary key constraint
-- The V2 migration created the table but forgot to add the PK
ALTER TABLE TB_USUARIO
ADD CONSTRAINT PK_USUARIO PRIMARY KEY (ID_USUARIO);

-- Add Usuario foreign key to TB_EMISSOES
-- This column was missing from the initial schema but exists in the Emissoes entity
ALTER TABLE TB_EMISSOES
ADD ID_USUARIO_FK INTEGER;

ALTER TABLE TB_EMISSOES
ADD CONSTRAINT FK_EMISSOES_USUARIO
    FOREIGN KEY (ID_USUARIO_FK)
    REFERENCES TB_USUARIO(ID_USUARIO);

-- Create index for better query performance
CREATE INDEX IDX_EMISSOES_USUARIO ON TB_EMISSOES(ID_USUARIO_FK);
