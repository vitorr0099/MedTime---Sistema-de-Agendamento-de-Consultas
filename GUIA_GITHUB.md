# 🚀 Guia Completo: Como Colocar o Projeto MedTime no GitHub

Este guia te ajudará a colocar seu projeto MedTime no GitHub de forma profissional.

## 📋 Pré-requisitos

1. **Conta no GitHub**: [Criar conta](https://github.com/signup)
2. **Git instalado**: [Baixar Git](https://git-scm.com/downloads)
3. **Projeto MedTime**: Seu projeto atual

## 🔧 Passo a Passo

### 1. Preparar o Projeto Local

Primeiro, vamos inicializar o Git no seu projeto:

```bash
# Navegar até a pasta do projeto
cd "C:\Users\vitor\OneDrive\Documentos\projeto java\medTime_20250623\medTime"

# Inicializar o repositório Git
git init

# Verificar o status
git status
```

### 2. Configurar o Git (se ainda não configurou)

```bash
# Configurar seu nome de usuário
git config --global user.name "Seu Nome"

# Configurar seu email
git config --global user.email "seu.email@exemplo.com"
```

### 3. Adicionar Arquivos ao Git

```bash
# Adicionar todos os arquivos
git add .

# Verificar o que foi adicionado
git status

# Fazer o primeiro commit
git commit -m "Initial commit: Sistema de agendamento médico MedTime

- Implementação do backend com Spring Boot
- Sistema de usuários (médicos e pacientes)
- Agendamento de consultas
- Interface web responsiva
- Diagramas UML incluídos"
```

### 4. Criar Repositório no GitHub

1. **Acesse**: [github.com](https://github.com)
2. **Faça login** na sua conta
3. **Clique** no botão verde "New" ou "+" no canto superior direito
4. **Preencha**:
   - **Repository name**: `medTime`
   - **Description**: `Sistema de agendamento médico desenvolvido em Java com Spring Boot`
   - **Visibility**: Public (ou Private se preferir)
   - **NÃO marque** "Add a README file" (já temos um)
   - **NÃO marque** "Add .gitignore" (já temos um)
   - **NÃO marque** "Choose a license" (já temos um)
5. **Clique** em "Create repository"

### 5. Conectar Repositório Local ao GitHub

Após criar o repositório, o GitHub mostrará comandos. Use estes:

```bash
# Conectar ao repositório remoto (substitua SEU_USUARIO pelo seu nome de usuário)
git remote add origin https://github.com/SEU_USUARIO/medTime.git

# Verificar se a conexão foi feita
git remote -v

# Enviar o código para o GitHub
git branch -M main
git push -u origin main
```

### 6. Verificar se Deu Certo

1. **Acesse** seu repositório no GitHub: `https://github.com/SEU_USUARIO/medTime`
2. **Verifique** se todos os arquivos estão lá
3. **Confirme** que o README.md está sendo exibido corretamente

## 🎨 Melhorando o Repositório

### Adicionar Topics (Tags)

No seu repositório no GitHub:
1. **Clique** em "About" (lado direito)
2. **Clique** no ícone de engrenagem
3. **Adicione** estas tags:
   ```
   java, spring-boot, medical-appointment, healthcare, jpa, hibernate, maven, docker
   ```

### Configurar Descrição

No mesmo local, adicione uma descrição:
```
🏥 Sistema de agendamento médico desenvolvido em Java com Spring Boot
```

### Adicionar Badges (Opcional)

Você pode adicionar badges no README.md para mostrar:
- Status do build
- Cobertura de testes
- Versão do Java
- Licença

## 📝 Comandos Git Úteis

### Para futuras atualizações:

```bash
# Ver status das mudanças
git status

# Adicionar mudanças
git add .

# Fazer commit
git commit -m "Descrição da mudança"

# Enviar para o GitHub
git push
```

### Para ver histórico:

```bash
# Ver commits
git log --oneline

# Ver diferenças
git diff
```

## 🔗 Links Úteis

- **GitHub**: https://github.com
- **Documentação Git**: https://git-scm.com/doc
- **GitHub Guides**: https://guides.github.com/
- **GitHub Desktop** (interface gráfica): https://desktop.github.com/

## 🎯 Próximos Passos

1. **Compartilhar**: Envie o link do repositório para seu professor
2. **Documentar**: Continue atualizando o README conforme necessário
3. **Colaborar**: Se trabalhar em grupo, configure colaboradores
4. **Issues**: Use issues para documentar bugs e melhorias
5. **Releases**: Crie releases para versões importantes

## 🆘 Solução de Problemas

### Erro de autenticação:
```bash
# Configurar token de acesso pessoal
git remote set-url origin https://SEU_TOKEN@github.com/SEU_USUARIO/medTime.git
```

### Arquivo muito grande:
```bash
# Verificar arquivos grandes
git rev-list --objects --all | git cat-file --batch-check='%(objecttype) %(objectname) %(objectsize) %(rest)' | sed -n 's/^blob //p' | sort -nr -k 2 | head -10
```

### Resetar último commit:
```bash
git reset --soft HEAD~1
```

## ✅ Checklist Final

- [ ] Git inicializado no projeto
- [ ] .gitignore configurado
- [ ] README.md criado
- [ ] LICENSE adicionado
- [ ] Repositório criado no GitHub
- [ ] Código enviado para o GitHub
- [ ] README sendo exibido corretamente
- [ ] Topics adicionados
- [ ] Descrição configurada

---

🎉 **Parabéns!** Seu projeto MedTime agora está no GitHub e pronto para ser compartilhado! 