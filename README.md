# Krypto

![logo](res/img/logo.png)

This is an app for Android to en- or decrypt messages.

Data privacy issues are of concern to users around the world. I propose a simple solution to keep
your correspondence private.

[![current version](https://img.shields.io/badge/current_version-2.0-green)](#Krypto)
[![GitHub license](https://img.shields.io/github/license/VitasSalvantes/Krypto)](https://github.com/VitasSalvantes/Krypto/blob/master/LICENSE)
[![GitHub issues](https://img.shields.io/github/issues/VitasSalvantes/Krypto)](https://github.com/VitasSalvantes/Krypto/issues)

## Table of contents

- [How does it work](#How-does-it-work)
- [Screenshots](#Screenshots)
- [Technologies](#Technologies)
- [Development progress](#Development-progress)
- [Project statistics](#Project-statistics)
- [How to install](#How-to-install)
- [How to contribute](#How-to-contribute)

## How does it work

The user creates correspondence – an object containing an encryption method, a key and the ability
to automatically copy messages to the clipboard. After that, the user can paste the copied text into
any messenger.

In order for the recipient to be able to decrypt the sent message, he needs to know the key. It’s
recommended that you report the key in person.

If you are interested in how ciphers work, you can find out in this app.

## Screenshots

*All screenshots are relevant for version 2.0*

Screen of all correspondences (home screen):

<img src="screenshots/screenshot_correspondences.png" alt="screenshot_correspondences" style="width:240px;height:500px;">

Screen of all ciphers:

<img src="screenshots/screenshot_ciphers.png" alt="screenshot_ciphers" style="width:240px;height:500px;">

Screen with info about Caesar Cipher:

<img src="screenshots/screenshot_caesar_cipher.png" alt="screenshot_caesar_cipher" style="width:240px;height:500px;">

Creating new correspondence screen (select cipher):

<img src="screenshots/screenshot_creating_cipher.png" alt="screenshot_creating_cipher" style="width:240px;height:500px;">

Creating new correspondence screen (input name):

<img src="screenshots/screenshot_creating_name.png" alt="screenshot_creating_name" style="width:240px;height:500px;">

Creating new correspondence screen (input key (not visible)):

<img src="screenshots/screenshot_creating_key_not_visible.png" alt="screenshot_creating_key_not_visible" style="width:240px;height:500px;">

Creating new correspondence screen (input key (visible)):

<img src="screenshots/screenshot_creating_key_visible.png" alt="screenshot_creating_key_visible" style="width:240px;height:500px;">

On the button CREATE click:

<img src="screenshots/screenshot_list_with_new_correspondence.png" alt="screenshot_list_with_new_correspondence" style="width:240px;height:500px;">

New correspondence screen:

<img src="screenshots/screenshot_new_correspondence.png" alt="screenshot_new_correspondence" style="width:240px;height:500px;">

New correspondence screen (input message):

<img src="screenshots/screenshot_new_correspondence_message.png" alt="screenshot_new_correspondence_message" style="width:240px;height:500px;">

New correspondence screen (encryption):

<img src="screenshots/screenshot_new_correspondence_encryption.png" alt="screenshot_new_correspondence_encryption" style="width:240px;height:500px;">

Dialog to remove the correspondence (after long click on the correspondence):

<img src="screenshots/screenshot_dialog.png" alt="screenshot_dialog" style="width:240px;height:500px;">

After removing the correspondence:

<img src="screenshots/screenshot_deleted_correspondence.png" alt="screenshot_deleted_correspondence" style="width:240px;height:500px;">

## Technologies

- The programming
  language [Kotlin](https://kotlinlang.org/) ([AdoptOpenJDK version 11.0.10](https://adoptopenjdk.net/))
  was chosen for development.

- Development was carried out in [Android Studio](https://developer.android.com/studio/).

- [The Room persistence library](https://developer.android.com/jetpack/androidx/releases/room) was
  used to interact with a database.

- [Jetpack Compose toolkit](https://developer.android.com/jetpack/compose) was used for building
  native UI.

- [Figma](https://www.figma.com/) was used to prototype the user interface.

- [WakaTime](https://wakatime.com/) was used to track activity.

- [Microsoft To Do](https://todo.microsoft.com/tasks/) was used to schedule tasks.

- [Flaticon](https://www.flaticon.com/free-icons/security) was used to create the logo.

## Development progress

Development of the project began on **Mar 10, 2021**.

Main problems:

- Scary UI

- Little of ciphers

- Insecure ciphers

Currently implemented ciphers:

- [Caesar cipher](https://github.com/VitasSalvantes/Simple-Examples#caesarcipher)

*Since I am developing this project mainly alone, I see no reason to
use [Trello](https://trello.com/).*

## Project statistics

[![wakatime](https://wakatime.com/badge/github/VitasSalvantes/Krypto.svg)](https://wakatime.com/badge/github/VitasSalvantes/Krypto)

See more on [WakaTime](https://wakatime.com/@VitasSalvantes/projects/xemqmsfaet).

## How to install

1. Download the repository code as a zip archive
2. Unpack the zip archive
3. Open the unpacked project with Android Studio
4. Run the project

## How to contribute

If you want to take part in the development of this project, please
read [this](/CONTRIBUTING.md) file.
