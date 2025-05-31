
# 🎬 Watch It

**Watch It** is an entertainment application that allows users to explore, subscribe to, and watch a collection of movies. The system tracks user subscriptions, watch history, and movie ratings, and provides administrative insights into platform performance.

---
## Table Of Content
- [Features](#features)
- [Main Entities](#main-entities)
- [File Management](#file-management)
- [Functionalities](#functionalities)
- [Constraints](#constraints)
- [Technical Requirements](#technical-requirements)
- [Deliverables](#deliverables)
- [Contributors](#contributors)

---
## Features

- **User Management**: Add, update, and delete users; track watch history and watchlist.
- **Movie Catalog**: Browse and search movies by title, genre, actor, or director.
- **Subscription Plans**: Basic, Standard, and Premium with tiered limits and automatic expiration after 30 days.
- **Ratings System**: Users can rate watched movies; ratings are used to update the overall movie rating.
- **Analytics**:
  - Admins can view the most subscribed plan.
  - Admins can check which month had the highest revenue.
  - Users can view top-rated, top-watched, and most recent movies.

---

## Main Entities

1. **User** – Tracks credentials, subscriptions, watchlist, and watch records.
2. **Subscription** – Stores plan type, price, and start date.
3. **Movie** – Includes metadata such as title, cast, genres, and financials.
4. **Cast** – Contains actor details and associated movies.
5. **Director** – Contains director details and filmography.
6. **User Watch Record** – Tracks what a user watched, when, and their rating.

---

## File Management

- Data is read from files at the beginning of the program and saved at the end.
- Only two methods are used for file I/O: one for reading and one for writing.

---

## Functionalities

- CRUD operations on all entities.
- View watchlist and watch history.
- Rate movies and dynamically update average ratings.
- View top-rated and top-watched movies.
- Search by title, director, actor, and genre.
- Admin analytics on revenue and subscription trends.
- Display cast and director details.

---

## Constraints

- Subscription validity: 30 days.
- Movie watch limits per plan:
  - Basic: 5 movies/month
  - Standard: 10 movies/month
  - Premium: 30 movies/month
- Users must resubscribe after plan expiration to access content.

---

## Technical Requirements

- Fully Object-Oriented (OOP) implementation
- At least 8 classes
- Exception handling
- GUI (bonus)
- No databases – use file storage
- Bonus for non-trivial features

---

## Deliverables

- Java project with all source files and classes
- Class diagram
- Documentation with:
  - System description
  - Input/output scenarios

---

## Contributors
|                   Name                    |     Github Link   |
| :---------------------------------------: | :--------: |
|     Tasneem Mohamed Ahmed Mohamed      |https://github.com/Tasneem357Mohamed |
| Bsmala Tarek kamal Khalil Elbagoury | https://github.com/Bsmalatarek |
|      Nada Ayman Mohamed Ismail      | https://github.com/Nada-Hany |
|          Toqa Karam Abddelmageed Madany        | https://github.com/Toka136 |
|           Yossef Ahmed Sayed Ibrahim         | https://github.com/youssefahmed123456 |
|       Yossef Mohssen Reda      | https://github.com/Youssef-Mohsen |
|       Mai Ezz Eldeen Saad      |      https://github.com/Maiezz      |
