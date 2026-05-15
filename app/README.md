# Rakta-Vahini : Smart Blood Donor Management System

## Overview

Rakta-Vahini is a smart Android application developed using Kotlin and Firebase that helps users register as blood donors, search eligible donors by blood group and city, maintain donation records, and manage emergency blood requests efficiently.
The application was designed to simplify the process of finding blood donors during medical emergencies through a centralized and user-friendly mobile platform.

## Problem Statement

During emergencies, finding blood donors quickly is difficult due to the lack of a centralized donor management system. Most people depend on phone calls, social media forwards, or personal contacts, which consumes valuable time.

Rakta-Vahini solves this problem by providing:

- Quick donor registration
- Blood group based search
- City based filtering
- Donation history tracking
- Eligibility management
- Firebase-based cloud storage

## Features

### User Authentication
- Firebase Authentication Login
- User Signup and Login
- Secure user session management

### Donor Registration
- Add donor details
- Store blood group, city, phone number
- Eligibility toggle for donation availability

### Find Donors
- Search donors by blood group
- Filter donors by city
- View donor eligibility status

### Donation Logging
- Maintain donation records
- Add hospital/camp donation entries
- Track donation history

### Statistics Dashboard
- View donation count
- Lives saved statistics
- Donor level system

### Firebase Integration
- Cloud Firestore database
- Real-time data storage
- Centralized donor management

## Technologies Used

- Kotlin
- Android Studio
- Firebase Authentication
- Firebase Firestore
- XML
- GitHub

## AI Studio Usage

AI Studio was used during the prototype planning phase for:
- UI ideation
- Workflow planning
- Feature analysis
- Screen structure generation
- Application architecture planning

The final implementation was developed using Android Studio and Kotlin.

## Project Structure

```text
Rakta-Vahini/
│
├── app/
│   ├── manifests/
│   ├── kotlin+java/
│   ├── res/
│   │   ├── layout/
│   │   ├── drawable/
│   │   ├── menu/
│   │   └── values/
│   └── build.gradle.kts
│
├── gradle/
├── settings.gradle.kts
└── README.md
```

## Installation Steps

### Step 1
Clone the repository

```bash
git clone https://github.com/rakshinaidu/Rakta-Vahini.git
```

### Step 2
Open the project in Android Studio

### Step 3
Sync Gradle files

### Step 4
Connect Firebase using:
- Firebase Authentication
- Firebase Firestore

### Step 5
Run the application on:
- Android Emulator
- Physical Android Device

## Application Modules

| Module | Description |
|---|---|
| Login Module | User authentication using Firebase |
| Profile Module | Donor profile management |
| Find Donor Module | Search donors using filters |
| Donation Log Module | Maintain donation history |
| Statistics Module | Display donor statistics |

## Future Scope

- Real-time emergency notifications
- GPS based donor location tracking
- Hospital integration
- AI-based donor recommendation
- Push notifications
- Nearby donor suggestions

## Developer Details

**Developer Name:** Rakshitha M  
**USN:** 1GD22CS041  
**Project Domain:** Healthcare  
**Platform:** Android

## GitHub Repository

https://github.com/rakshinaidu/Rakta-Vahini
