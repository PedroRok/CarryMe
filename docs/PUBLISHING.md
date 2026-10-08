# Publicando o Carry Me no CurseForge e no Modrinth

O upload é feito pelo Gradle com o [mod-publish-plugin](https://modmuss50.github.io/mod-publish-plugin/),
configurado no `build.gradle` da raiz. Um único comando publica os jars de Fabric, Forge e NeoForge
nas duas plataformas.

## Pré-requisitos (uma vez só)

Crie os tokens e salve-os como variáveis de ambiente do usuário:

| Variável           | Onde gerar                                                     |
|--------------------|----------------------------------------------------------------|
| `CURSEFORGE_TOKEN` | https://legacy.curseforge.com/account/api-tokens               |
| `MODRINTH_TOKEN`   | Modrinth > Settings > PATs (escopo "Create versions")          |

No Prompt de Comando ou Git Bash:

```cmd
setx CURSEFORGE_TOKEN "cole-o-token"
setx MODRINTH_TOKEN "cole-o-token"
```

Feche e reabra o terminal depois disso. Processos já abertos não recebem
variáveis novas.

Nunca coloque os tokens no `gradle.properties` nem em qualquer arquivo versionado.

## Passo a passo de cada release

1. **Atualize a versão** em `gradle.properties`:
   - `mod_version`: versão do mod (ex.: `1.1`).
   - `minecraft_version`: versão do Minecraft alvo. Ela entra no nome do arquivo e na lista de
     versões suportadas nas duas plataformas.
2. **Escreva o changelog** em `CHANGELOG.md` na raiz. O conteúdo inteiro do arquivo vira o texto
   da release, em Markdown, no CurseForge e no Modrinth.
3. **Teste sem enviar nada** (opcional, mas recomendado):

   ```
   ./gradlew publishMods -PdryRun
   ```

   O build completo roda e os arquivos que seriam enviados ficam em `build/publishMods/`.
   Confira se os três jars estão lá com a versão certa.
4. **Publique**:

   ```
   ./gradlew publishMods
   ```

5. **Confira**:
   - Modrinth: https://modrinth.com/mod/carry-me/versions (aparece na hora).
   - CurseForge: https://www.curseforge.com/minecraft/mc-mods/carry-me/files (os arquivos passam
     pela moderação e podem levar de minutos a algumas horas para ficarem visíveis).
6. **Commite** `gradle.properties` e `CHANGELOG.md` e crie uma tag com a versão.

## Variações úteis

| Comando                                     | Efeito                                              |
|---------------------------------------------|-----------------------------------------------------|
| `./gradlew publishMods -PreleaseType=BETA`  | Marca a release como beta (`ALPHA` também funciona)  |
| `./gradlew publishModrinthFabric`           | Publica só um alvo                                   |
| `./gradlew publishCurseforgeNeoForge`       | Idem                                                 |

Os alvos disponíveis são `publishCurseforgeFabric`, `publishCurseforgeForge`,
`publishCurseforgeNeoForge`, `publishModrinthFabric`, `publishModrinthForge` e
`publishModrinthNeoForge`.

## Se os tokens não forem encontrados

O erro aparece como falha de autenticação ou como propriedade `accessToken` vazia. Causas comuns:

- O terminal foi aberto antes de rodar o `setx`. Feche e abra de novo.
- O token foi definido com `$env:NOME=...`, que só vale naquela janela do PowerShell.

Para publicar de um terminal que ainda não recebeu as variáveis, carregue-as do perfil do usuário
na hora:

```powershell
$env:CURSEFORGE_TOKEN = [Environment]::GetEnvironmentVariable('CURSEFORGE_TOKEN','User')
$env:MODRINTH_TOKEN = [Environment]::GetEnvironmentVariable('MODRINTH_TOKEN','User')
./gradlew publishMods
```

## O que está configurado no `build.gradle`

- **CurseForge**: projeto `1421860` (slug `carry-me`), marcado como client e server.
- **Modrinth**: projeto `TqKRgEs9`, ambiente client e server.
- **Dependências declaradas**: `carry-on` obrigatório em todos os loaders e `fabric-api` obrigatório
  só no Fabric.
- **Nome exibido**: `Carry Me <mod_version> - <Loader> <minecraft_version>`.
- **Versão**: o valor de `mod_version`.

Para suportar mais de uma versão do Minecraft no mesmo upload, adicione as versões extras com
`minecraftVersions.add(...)` nos blocos `curseforgeOptions` e `modrinthOptions`.
