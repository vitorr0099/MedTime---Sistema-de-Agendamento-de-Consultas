# Configuração do Campo photo_url para Médicos

## Mudanças Realizadas

### 1. Arquivo JavaScript Principal (`api.js`)
- **Localização**: `src/main/resources/templates/Trabalho/js/api.js`
- **Mudança**: Alterado o campo de `photoUrl` para `photo_url` para corresponder ao modelo do banco de dados
- **Fallback**: Se `photo_url` estiver vazio ou nulo, será usada a imagem `/logo/logo.png`

### 2. Arquivo HTML de Médicos (`medicos.html`)
- **Localização**: `src/main/resources/templates/Trabalho/medicos.html`
- **Mudança**: Alterado o campo de `photoUrl` para `photo_url`
- **Fallback**: Se `photo_url` estiver vazio ou nulo, será usada a imagem `/logo/logo.png`

### 3. Remoção de Imagens Aleatórias
- Removida a lógica que gerava URLs aleatórias do `randomuser.me`
- Removida a lógica que gerava URLs aleatórias do `pravatar.cc`
- Agora o sistema usa exclusivamente o campo `photo_url` do banco de dados

## Como Usar

### 1. Inserir Dados no Banco
Use o arquivo `example_doctors_data.sql` como exemplo para inserir médicos com o campo `photo_url`:

```sql
INSERT INTO doctors (name, crm, photo_url, specialization_id) VALUES 
('Dr. Lucas Silva', 'CRM-12345', '/logo/lucas.png', 1);
```

### 2. Caminhos das Imagens
- As imagens devem estar no diretório `src/main/resources/static/logo/`
- O caminho no banco deve começar com `/logo/` (ex: `/logo/lucas.png`)
- Se a imagem não existir, será usada `/logo/logo.png` como padrão

### 3. Estrutura do Banco de Dados
O campo `photo_url` na tabela `doctors` deve conter o caminho relativo da imagem:
- Exemplo: `/logo/lucas.png`
- Exemplo: `/logo/beatriz.png`
- Se vazio ou nulo: usa imagem padrão

## Arquivos Modificados

1. `src/main/resources/templates/Trabalho/js/api.js` - Linha 90-102
2. `src/main/resources/templates/Trabalho/medicos.html` - Linha 242
3. `example_doctors_data.sql` - Novo arquivo com exemplos
4. `README_PHOTO_URL.md` - Este arquivo de documentação

## Benefícios

- **Consistência**: Todas as imagens são controladas pelo banco de dados
- **Flexibilidade**: Fácil alteração de imagens sem modificar código
- **Performance**: Não há mais chamadas para APIs externas de imagens aleatórias
- **Manutenibilidade**: Código mais limpo e previsível 