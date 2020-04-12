# {{name}}

A Leiningen template for FIXME.

## Project Setup

Congratulations, you have already successfully setup a _rum-natal_ project. This is only half of the work you need to do before you can begin to build you React Native app with ClojureScript. Please follow the next steps to finish setting you project up.

You can find the whole documentation on rum-natal `docs/rum-natal/usage.md`

### React Native Boilerplate

Your project directory only contains the Clojure part yet. Now go to the projects parent directory and initalize a new React Native project in the project directory you just created.
```bash
react-native init <project-name> --version 0.62.2
```

Now you should have a complete React Native app sceleton in your project directory. You can delete the following files created by React Native because we don't need them:
- _App.js_
- _index.js_

Last you should add the following to your `.gitignore` file to keep your repository clean:
```
# Clojure / Figwheel
#
target/
figwheel_server.log
.lein-repl-history
index.android.js
index.ios.js
```

### Run you App

First install your JavaScript dependencies with yarn by running `yarn` in your terminal.

To run your app you have to build the JavaScript index files for booth platforms. Note that those files only collect the required modules including react, the native components you use, media assets and the app's code wrapped in a fighweel bridge. The app's code created with figwheel in dev mode resides in the `target/` directory.

Too build booth `index.android.js` and `index.ios.js` run
```bash
lein collect-dev
```

Now you can start figwheel and run your app either on a real device or in a simulator:
```bash
lein figgheel android
yarn start
npx react-native run-android
```
To run your app on iOS just interchange _android_ with _ios_.



## Usage

FIXME

## License

Copyright © 2020 FIXME

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
