
# 🎬 Watch It

**Watch It** is an entertainment application that allows users to explore, subscribe to, and watch a collection of movies. The system tracks user subscriptions, watch history, and movie ratings, and provides administrative insights into platform performance.

---

## 📌 Features

- **User Management**: Add, update, and delete users; track watch history and watchlist.
- **Movie Catalog**: Browse and search movies by title, genre, actor, or director.
- **Subscription Plans**: Basic, Standard, and Premium with tiered limits and automatic expiration after 30 days.
- **Ratings System**: Users can rate watched movies; ratings are used to update the overall movie rating.
- **Analytics**:
  - Admins can view the most subscribed plan.
  - Admins can check which month had the highest revenue.
  - Users can view top-rated, top-watched, and most recent movies.

---

## 🧱 Main Entities

1. **User** – Tracks credentials, subscriptions, watchlist, and watch records.
2. **Subscription** – Stores plan type, price, and start date.
3. **Movie** – Includes metadata such as title, cast, genres, and financials.
4. **Cast** – Contains actor details and associated movies.
5. **Director** – Contains director details and filmography.
6. **User Watch Record** – Tracks what a user watched, when, and their rating.

---

## 📁 File Management

- Data is read from files at the beginning of the program and saved at the end.
- Only two methods are used for file I/O: one for reading and one for writing.

---

## 💻 Functionalities

- CRUD operations on all entities.
- View watchlist and watch history.
- Rate movies and dynamically update average ratings.
- View top-rated and top-watched movies.
- Search by title, director, actor, and genre.
- Admin analytics on revenue and subscription trends.
- Display cast and director details.

---

## 🎯 Constraints

- Subscription validity: 30 days.
- Movie watch limits per plan:
  - Basic: 5 movies/month
  - Standard: 10 movies/month
  - Premium: 30 movies/month
- Users must resubscribe after plan expiration to access content.

---

## 🧰 Technical Requirements

- Fully Object-Oriented (OOP) implementation
- At least 8 classes
- Exception handling
- GUI (bonus)
- No databases – use file storage
- Bonus for non-trivial features

---

## 📄 Deliverables

- ✅ Java project with all source files and classes
- ✅ Class diagram
- ✅ Documentation with:
  - System description
  - Input/output scenarios

---

## 👥 Contributors

Each team member should contribute to at least one major class, GUI (if implemented), or file handling module.

---

## 🚀 Getting Started

1. Clone the repo
2. Compile and run the main Java class
3. Interact with the GUI or console interface (if provided)
4. Make sure to run the project from a directory containing the data files

---

## 🔒 License

This project is for educational use only.
