# {{name}}

Congratulations, you have already successfully setup a _rum-natal_ project and you are ready to build mobile apps with ClojureScript and React Native.

You can find the whole documentation on rum-natal in `docs/rum-natal/usage.md`.

## Run you App

First install your JavaScript dependencies with yarn by running `yarn` in your terminal.

To run your app you have to build the JavaScript index files for booth platforms. Note that those files only collect the required modules including react, the native components you use, media assets and the app's code wrapped by the fighweel bridge. The app's code created with figwheel in development mode resides in the `target/` directory.

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

#### Running on an Android Device

If you run your app on an Android Device you will have to start adb and add some rules to allow traffic to flow between your host machine and your Android Device:
```bash
adb start-server
adb reverse tcp:8081 tcp:8081
adb reverse tcp:3449 tcp:3449
```

It might be necessary to run the commands above with sudo.

### Build a Production Version of your App

To build a production version of your app you can either create a production build or an advanced production build by running one of the following commands:
```bash
lein prod-build
# or
lein advanced-build
```

To create either a runnable Android or iOS version please follow the official React Native guides from here.

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
