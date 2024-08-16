# AL_Base_Plugin Changelog
___
## Version: 1.0:
### New:
+ added the /stockpile command with the syntax `/stockpile [info|stored|add]` and the staff sub-command `/stockpile stored [faction]`
+ implemented a feature toggle within the config.yml
+ added a TabCompletion for the /stockpile command
+ added the /rpchar command with the syntax `/rpchar [ign] [staff] {title}`
+ added the /reload command with the syntax `/alreload {base|stockpile|factions}`
+ added a TabCompletion for the /reload command
+ added a TabCompletion for the /rpchar command
+ the plugin is now loading the faction list dynamically from the backend upon startup
+ added the staff command /leaderactivity with the syntax `/leaderactivity {faction}`
+ added a TabCompletion for the /leaderactivity command
___
## Version: 1.0.1:
### Changed:
* the Command /rpchar now generates the title without an appended space
___
## Version: 1.0.2:
### Changed:
* added a config to toggle the backend connection attempt at the start of the server
___
## Version: 1.0.3:
### New:
* add the command /alutil with TabCompletion
