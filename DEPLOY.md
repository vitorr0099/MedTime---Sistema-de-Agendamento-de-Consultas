# Deploy do MedTime no Render

## Pré-requisitos

1. Conta no GitHub
2. Conta no Render (gratuita)
3. Banco de dados MySQL (pode usar o MySQL do Render ou externo)

## Passo a Passo

### 1. Preparar o Repositório

1. Faça commit e push de todos os arquivos para o GitHub:
```bash
git add .
git commit -m "Preparando para deploy no Render"
git push origin main
```

### 2. Criar Conta no Render

1. Acesse [render.com](https://render.com)
2. Faça login com sua conta GitHub
3. Clique em "New +" e selecione "Web Service"

### 3. Conectar Repositório

1. Selecione o repositório `medTime` do GitHub
2. Configure as seguintes opções:
   - **Name**: `medtime-backend`
   - **Environment**: `Docker`
   - **Region**: Escolha a mais próxima
   - **Branch**: `main`
   - **Build Command**: `mvn clean package -DskipTests`
   - **Start Command**: `java -jar target/medTime-0.0.1-SNAPSHOT.jar`

### 4. Configurar Variáveis de Ambiente

Adicione as seguintes variáveis de ambiente no Render:

- `SPRING_PROFILES_ACTIVE`: `production`
- `DATABASE_URL`: URL do seu banco MySQL
- `DATABASE_USERNAME`: Usuário do banco
- `DATABASE_PASSWORD`: Senha do banco
- `ADMIN_USERNAME`: Usuário admin (opcional)
- `ADMIN_PASSWORD`: Senha admin (opcional)

### 5. Banco de Dados

**Opção 1 - MySQL no Render:**
1. Crie um novo "PostgreSQL" (Render não tem MySQL gratuito)
2. Use as credenciais fornecidas pelo Render

**Opção 2 - Banco Externo:**
- PlanetScale (MySQL gratuito)
- Railway (MySQL)
- Clever Cloud (MySQL)

### 6. Deploy

1. Clique em "Create Web Service"
2. Aguarde o build e deploy (pode demorar alguns minutos)
3. Acesse a URL fornecida pelo Render

## URLs Importantes

- **Aplicação**: `https://medtime-backend.onrender.com`
- **API**: `https://medtime-backend.onrender.com/api/`

## Troubleshooting

### Erro de Build
- Verifique se o Maven está configurado corretamente
- Confirme se todas as dependências estão no `pom.xml`

### Erro de Conexão com Banco
- Verifique as variáveis de ambiente
- Confirme se o banco está acessível
- Teste a conexão localmente

### Erro de Porta
- Certifique-se de que a aplicação está usando a porta `$PORT` (variável do Render)

## Monitoramento

- Use o dashboard do Render para monitorar logs
- Configure alertas se necessário
- Monitore o uso de recursos 