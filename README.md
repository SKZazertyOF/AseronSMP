# AseronSMP Plugin

Un plugin Minecraft développé pour enrichir l’expérience de jeu sur le serveur **AseronSMP**.  
Il ajoute de nouveaux objets spéciaux, des recettes personnalisées, des commandes pratiques et des mécaniques uniques.

## Fonctionnalités
- Items custom avec effets spéciaux :
    - DragonSword (nécessite ItemsAdder pour fonctionner pleinement).
    - NekoHammer, BlessedPickaxe, MysticBow, etc.
- Armures spéciales appliquant des effets automatiques (OracleMask, BrasierChestplate, GardienLeggings, VagueBoots).
- Recettes personnalisées.
- Commandes utiles :
    - `/crafts` : affiche les crafts disponibles.
    - `/ae` : donne les objets spéciaux.
    - `/spawn` : téléportation et gestion des dégâts associés.
- Gestion des permissions et des opérateurs via `OpManager`.

## Installation
1. Télécharge la dernière version de **AseronSMPPlugin**.
2. Place le fichier `.jar` dans le dossier `plugins` de ton serveur Minecraft.
3. **Installe également [ItemsAdder](https://www.spigotmc.org/resources/itemsadder.73355/), obligatoire pour certaines fonctionnalités.**
4. Redémarre ton serveur.
5. Les nouveaux items et commandes sont prêts à l’emploi.

## Commandes
| Commande    | Description                               | Permission            |
|-------------|-------------------------------------------|-----------------------|
| `/crafts`   | Affiche les crafts personnalisés          | `aseron.crafts`       |
| `/ae`       | Donne les objets spéciaux                 | `aseron.ae`           |
| `/spawn`    | Téléportation au spawn + événements liés  | `aseron.spawn`        |

## Compatibilité
- Version Minecraft : **1.20.1**.
- Dépendances :
    - [ItemsAdder](https://www.spigotmc.org/resources/itemsadder.73355/) (nécessaire pour certains items et effets).

## Développement
Ce plugin a été développé en Java avec l’API **Spigot/Paper**.  
Il utilise des gestionnaires d’événements (`Listener`), des commandes personnalisées et des tâches planifiées avec `BukkitRunnable`.

## Support
Pour toute question ou suggestion :
- Ouvre une issue sur ce dépôt GitHub.
- Ou contacte-moi sur **Discord : SKZ_azerty**.
