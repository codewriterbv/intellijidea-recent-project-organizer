# Recent Projects Organizer

A plugin for the IntelliJ platform to organize the list in "Recent Projects".

Detailed info is available in [this blog post](https://webtechie.be/post/2025-09-18-jetbrains-intellijidea-plugin-recent-project-organizer/).

[![Watch the demo](https://img.youtube.com/vi/d0j_PVqXxRc/0.jpg)](https://www.youtube.com/watch?v=d0j_PVqXxRc)

## Description

This plugin groups the recent projects by the first word in their (display) name by space, "-", "_", or ".". When multiple projects have the same first word, they are grouped in a sub-group.

Existing "Recent Projects":

![Recent Projects](screenshots/recent-projects.png)

New "File" option added by this plugin: "Recent Projects (Organized)":

![Recent Projects Organized](screenshots/recent-projects-organized.png)

### Existing Functionality in IntelliJ IDEA

This plugin adds an extra option to the "File" menu, but thanks to [Marit van Dijk](https://github.com/mlvandijk), I learned some similar functionality is available by default in IntelliJ IDEA. 

* Click on the name of the project you are working on, and you will get a list with Open and Recent projects. Start typing while this list is open, and the list will be filtered.
* In general, most menus of IntelliJ IDEA are searchable, just start typing...
* In the documentation: [Navigate projects on the Welcome screen](https://www.jetbrains.com/help/idea/open-close-and-move-projects.html#navigate-welcome-screen)

## Installation

Use the IDE's built-in plugin system:

* `File` --> `Settings...` --> `Plugins` --> `Marketplace`
* search for: `Recent Projects Organizer`
* click the `Install`-button

Or go to the [plugin page](https://plugins.jetbrains.com/plugin/28455-recent-projects-organizer) on the [JetBrains](https://www.jetbrains.com)-website, download the archive-file and install manually.

## Development

To try the plugin during development:

```
./gradlew runIde
```

This will:

* Build your plugin
* Launch a new IntelliJ IDEA instance with your plugin installed
* Allow you to test your plugin in a real environment

To debug the plugin:

* Via Gradle Tool Window
* Navigate to: Tasks → intellij → runIde
* Right-click on runIde → Debug 'runIde'

## License

Please read the [license](LICENSE) file.

## Credits

Created by [Frank Delporte](https://www.linkedin.com/in/frankdelporte/) for [CodeWriter bv](https://codewriter.be/) as an open-source project.

## Donate

If you like this plugin, please consider a [donation](https://buymeacoffee.com/frankdelporte). Thank you!
