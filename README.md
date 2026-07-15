<div align="center">

# PharmaPlus

**An e-commerce pharmacy management app for Android — order medicines, manage a cart, check out, and run the whole store from an admin panel.**

Built as our **CSE 3120 – Software Engineering & Information Systems** project in **Android Studio Jellyfish (Java)**.

</div>

<table align="center">
<tr><td>

### Try it yourself

You don't need Android Studio to take it for a spin — pick whichever is easier:

[![Live Demo](https://img.shields.io/badge/▶%20Run%20it%20online-Appetize.io-4353FF?style=for-the-badge)](https://appetize.io/app/b_fqpatdn3y2om3igenezwes5jcy)
[![Download APK](https://img.shields.io/badge/⬇%20Download%20APK-Google%20Drive-34A853?style=for-the-badge&logo=googledrive&logoColor=white)](https://drive.google.com/file/d/1lj6pCWnIFUt1xzNEb7UPq-LZ4lTBojo9/view?usp=sharing)

- **Run it in the browser** — the Appetize.io link, no install, just click and wait a few seconds for the emulator to boot
- **Install the real thing** — download the APK and sideload it on any Android device running Android 9 (Pie) or newer

**Login credentials** — the admin account unlocks product management, the user account is the normal shopping experience:

| Role  | Email / Username | Password   |
|-------|------------------|------------|
| Admin | `admin`          | `admin`    |
| User  | `test@gmail.com` | `test@123` |

Log in as the user to browse, add to cart and place an order — then log in as admin to add, edit or remove products and watch the catalog change.

</td></tr>
</table>

---

## About the project

PharmaPlus is a two-sided pharmacy app. On one side, customers can browse medicines and health products by category, open a product to read the details, drop items into a cart, save a delivery address and place an order. On the other side, an admin panel lets the store owner add new products (with images), modify existing ones and delete what's out of stock — all backed by Firebase so changes show up live.

It started as a coursework project, but we treated it like a real product: proper architecture, design patterns where they actually made sense, and an automated test + build pipeline on GitHub.

## Features

**For customers**
- Sign up / log in with Firebase Authentication
- Browse products by category — Medication, Health and Personal Care
- Product detail pages with images and a one-tap add-to-cart
- A cart you can edit before checking out
- Save and pick from multiple delivery addresses
- Place orders and review your past orders
- Payment flow and an order-confirmation screen

**For the admin**
- Dedicated admin login and panel
- Add new products with images (stored in Firebase Storage)
- Modify or delete existing products
- Everything syncs to the storefront in real time

## Tech stack

| Area | What we used |
|------|--------------|
| Language | Java |
| Backend / DB | Firebase Authentication, Cloud Firestore, Realtime Database, Cloud Storage |
| Networking | Volley |
| Images & animation | Glide, Lottie, RoundedImageView |
| UI | Material Components, ViewBinding, Navigation Component, LiveData & ViewModel |
| Build | Gradle (Kotlin DSL), `minSdk 28`, `targetSdk 34` |
| CI | GitHub Actions |

## Design patterns

We didn't sprinkle patterns for the sake of it — each one solved a real problem:

- **Singleton** — a single shared instance for the `User` and product models
- **Factory** — `ProductQueryFactory` builds the right Firestore query object per category (medication / health / personal)
- **Observer** — the storefront reacts when a new product is added
- **Adapter** — RecyclerView adapters for categories, suggested items, cart, orders and the "view all" lists

## Testing & CI

- **Unit testing** with JUnit, Mockito and Robolectric (runs on the JVM, no emulator needed)
- **UI / instrumentation testing** with Espresso
- **Continuous integration** via GitHub Actions — every push and pull request to `main` builds the app and uploads the APK as a downloadable artifact

---

## Architecture & Diagrams

> These are trimmed-down versions of the design for readability — the full app has 20+ functionalities.

### Class Diagram
![Class Diagram](https://github.com/Mofazzal874/PharmaPlus/assets/35369040/ffbc2f6e-755f-44f9-9509-0ec8e5d9a210)

### Sequence Diagram
![Sequence Diagram](https://github.com/Mofazzal874/PharmaPlus/assets/35369040/f0dbce9a-3166-4189-a150-d2021de09d78)

### State Diagram
![State Diagram](https://github.com/Mofazzal874/PharmaPlus/assets/35369040/7c38caac-e3ac-4dc2-bb45-a1beea9ebb6f)

### Activity Diagram
![Activity Diagram](https://github.com/Mofazzal874/PharmaPlus/assets/35369040/a3abf6e5-9038-4c6f-bb35-5d9cab132c66)

---

## Screenshots

<table>
  <tr>
    <td><img src="https://github.com/Mofazzal874/PharmaPlus/assets/35369040/81312d9a-fe0f-4f99-92d8-c0332dc5b6cc" width="260"/></td>
    <td><img src="https://github.com/Mofazzal874/PharmaPlus/assets/35369040/899c4921-353b-4cfe-8ad1-04756a503c78" width="260"/></td>
    <td><img src="https://github.com/Mofazzal874/PharmaPlus/assets/35369040/c7f95a16-c31a-4899-ab89-5f96e4c4e44c" width="260"/></td>
  </tr>
  <tr>
    <td><img src="https://github.com/Mofazzal874/PharmaPlus/assets/35369040/898cb593-df7b-4346-91dc-731efce0ed89" width="260"/></td>
    <td><img src="https://github.com/Mofazzal874/PharmaPlus/assets/35369040/74369e86-2c74-4578-a099-fe042f76cf91" width="260"/></td>
    <td><img src="https://github.com/Mofazzal874/PharmaPlus/assets/35369040/dd082f8c-09b5-4dd1-b9a1-221cb4ec2857" width="260"/></td>
  </tr>
  <tr>
    <td><img src="https://github.com/Mofazzal874/PharmaPlus/assets/35369040/322b76fb-91bc-4952-b54f-4cb33363528c" width="260"/></td>
    <td><img src="https://github.com/Mofazzal874/PharmaPlus/assets/35369040/d49fcc6d-7dc7-4521-a1d1-de9b0031800e" width="260"/></td>
    <td><img src="https://github.com/Mofazzal874/PharmaPlus/assets/35369040/49cf6e1a-c494-4502-9ce4-bebc78da998d" width="260"/></td>
  </tr>
  <tr>
    <td><img src="https://github.com/Mofazzal874/PharmaPlus/assets/35369040/e39ee17b-33a1-4169-bc0a-d7a888ece65d" width="260"/></td>
    <td><img src="https://github.com/Mofazzal874/PharmaPlus/assets/35369040/80e154bf-da76-4c8e-b10b-91867dd46d34" width="260"/></td>
    <td></td>
  </tr>
</table>

---

## The team

This was a collaborative project by:

- [Ekramul Alam Apan (2007071)](https://github.com/Apn7)
- [Amit Kairy (2007069)](https://github.com/superXnova21)
- [Mofazzal Hosen (2007074)](https://github.com/Mofazzal874)
