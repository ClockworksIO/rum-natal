# Rum-Natal

[Rum-Natal](https://github.com/ClockworksIO/rum-natal) is a simple figwheel boilerplate to help developing [React Native](https://reactnative.dev/) apps with [Clojurescript](https://clojurescript.org/). It uses [Rum](https://github.com/tonsky/rum) as a react wrapper. It should be straight forward to adapt rum-natal to be able to use other react wrappers.

Rum-Natal is based on project [re-natal](https://github.com/drapanjanas/re-natal).

A main difference to re-natal in terms of additional features is the support for the currently newest react-native version _0.63.2_ that includes AndroidX support.

## Limitations

As of now rum-natal is still in an early phase and should be used with care. It is compatibility with different versions of React and React Native as well as with different third party components is not tested well (if at all).

That being sad rum-natal is a collection of code derived from project re-natal and two recent projects I build with Clojurescript and React Native with success.

At this time the format of media assets supported by the automatic asset collection is very limited.

## Dependencies and Compatibility

Rum-Natal was created and is tested with
- `react-native 0.63.2`
- `react 16.13.1`
- `create-react-class 15.7.0`
- `clojure 1.10`
- `clojurescript 1.10.597`
- `leiningen 2.9.1`
- `lein-figwheel 0.5.19`

## Project Setup

### Basic Setup

To create a new project with rum-natal begin with creating a React Native project:
```sh
react-native init <projectName> --version 0.63.2
```
Keep in mind that the project name should be either be camel case or snake case. The kebab case style commonly used for Clojure modules is incompatible with React Native project names.

After you have setup the React Native project create the necessary Clojure project files by running the following command still from the projects parent directory:
```sh
lein new rum-natal <project-name> --to-dir ./<projectName> --force
```
You have to force leiningen to use the existing directory. As you can see at this point you should use kebab case style for the Clojure project name.

Now go to the project directory and finish setting up dependencies and version control.

As of React Native 0.63.x and newer you have to install the following additional dependencies:
```bash
yarn add create-react-class
```

Now you should have a complete React Native app skeleton in your project directory. You can delete the following files created by React Native because we don't need them:
- _App.js_
- _index.js_

Last you should add the following to your `.gitignore` file to keep your repository clean:
```bash
# Clojure / Figwheel
#
target/
figwheel_server.log
.lein-repl-history
index.android.js
index.ios.js
```

### Native Modules

A React Native app might required some native modules as external dependencies. React and React Native are themselves 'native' modules. To allow rum-natal to find those native modules and add them as a dependency to the index files during development, those modules must be added to the configuration file `rum-natal.edn` in the `:native-modules` vector.

**Hint**: This does not free you from adding those modules to your React Native project! You still have to add the modules as dependency to your `package.json` file with yarn and you must link the modules using the React Native command-line tool.

### Run you App

First install your JavaScript dependencies with yarn by running `yarn` in your terminal.

To run your app you have to build the JavaScript index files for booth platforms. Note that those files only collect the required modules including react, the native components you use, media assets and the app's code wrapped in a fighweel bridge. The app's code created with figwheel in developemnt mode resides in the `target/` directory.

Too build booth `index.android.js` and `index.ios.js` run
```bash
lein collect-dev
```

Now you can start figwheel and run your app either on a real device or in a simulator:
```bash
lein figwheel android
yarn start
yarn android
```
To run your app on iOS just interchange _android_ with _ios_.


## Run App in Development Mode

First install all required dependencies by running `yarn` and `lein deps` in the project directory.

To run your app you have to build the JavaScript index files for booth platforms. Note that those files only collect the required modules including react, the native components you use, media assets and the app's code wrapped in a fighweel bridge. The app's code created with figwheel in dev mode resides in the `target/` directory.

Too build booth `index.android.js` and `index.ios.js` run
```bash
lein collect-dev
```

**Note**: You have to re-run this command every time you add media assets or when switching from production build to development build!

### Android

First set if you would like to use either a virtual or a real device. In case you use a real device you have to start adb and reverse ports:
```
sudo adb start-server
sudo adb reverse tcp:8081 tcp:8081
sudo adb reverse tcp:3449 tcp:3449
```

Start a figwheel session in a console `lein figwheel android`.

In another console you can now run the react native packager and debugger with `yarn start`. Then you can run the app on your phone or virtual device by running `npx android`.


## Production Build

To build your app for production without the figwheel bridge and development tools run
```bash
lein prod-build
```

By running this command booth `index.android.js` and `index.ios.js` are overwritten and then contain the code of your app instead of including only your native modules, assets and wrapping your app with figwheel.
