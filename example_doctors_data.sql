-- Exemplo de inserção de médicos com campos completos
-- Este arquivo mostra como inserir dados na tabela doctors com todos os campos

-- Primeiro, certifique-se de que as especialidades existem
INSERT INTO specializations (name) VALUES 
('Cardiologia'),
('Neurologia'),
('Ginecologia'),
('Ortopedia')
ON DUPLICATE KEY UPDATE name = VALUES(name);

-- Inserir médicos com todos os dados
INSERT INTO doctors (name, crm, email, description, photo_url, specialization_id) VALUES 
('Dr. Lucas Silva', 'CRM-12345', 'lucas.silva@medtime.com', 'Cardiologista com 10 anos de experiência, especialista em arritmias e doenças coronarianas. Focado em medicina preventiva e bem-estar do paciente.', '/logo/lucas.png', 1),
('Dra. Beatriz Santos', 'CRM-12346', 'beatriz.santos@medtime.com', 'Especialista em cardiologia pediátrica, com vasta experiência no tratamento de cardiopatias congênitas.', '/logo/beatriz.png', 1),
('Dr. Rafael Costa', 'CRM-12347', 'rafael.costa@medtime.com', 'Neurologista renomado, com foco em doenças neurodegenerativas como Alzheimer e Parkinson.', '/logo/rafael.png', 2),
('Dra. Samira Hadid', 'CRM-12348', 'samira.hadid@medtime.com', 'Ginecologista e obstetra, apaixonada por saúde da mulher e acompanhamento pré-natal de alto risco.', '/logo/samira.png', 3)
ON DUPLICATE KEY UPDATE 
    name = VALUES(name),
    crm = VALUES(crm),
    email = VALUES(email),
    description = VALUES(description),
    photo_url = VALUES(photo_url),
    specialization_id = VALUES(specialization_id);

-- Nota: Os caminhos das imagens devem corresponder aos arquivos existentes no diretório /logo/
-- Se uma imagem não existir, o sistema usará a imagem padrão /logo/logo.png 