# Library Management System - Assignment 2

### Student Details
- **Name:** Prakhar Tiwari
- **Roll No:** 2100320100123
- **Batch:** Java Full Stack Development (ABESEC - HCL Tech Training)

---

## 1. How to Compile and Run

Open terminal and navigate to the `Assignment` folder:

```bash
javac -d out src/com/hcl/library/model/*.java src/com/hcl/library/exception/*.java src/com/hcl/library/repository/*.java src/com/hcl/library/util/*.java src/com/hcl/library/service/*.java src/com/hcl/library/Main.java
java -cp out com.hcl.library.Main
```

---

## 2. Design Note & Class Relationships

- **Borrowable (Interface):** Declares `isAvailable()`, `borrow()`, and `giveBack()`.
- **LibraryItem (Abstract Class):** Implements `Borrowable` and `Comparable<LibraryItem>`. Contains common fields (`id`, `title`, `year`, `available`), abstract method `lateFee(int daysLate)`, and overrides `equals` & `hashCode` on `id`.
- **Book, Magazine, DVD (Classes):** Subclasses extending `LibraryItem`.
  - `Book`: has `author`, `isbn`, late fee Rs. 2.0/day.
  - `Magazine`: has `issueNumber`, late fee Rs. 1.0/day.
  - `DVD`: has `durationMinutes`, late fee Rs. 5.0/day.
- **Member (Class):** Represents a member with `id`, `name`, validated `email` (must contain `@`), and a list of borrowed items.
- **Exceptions:**
  - `LibraryException` (unchecked base class extending `RuntimeException`)
  - `ItemNotFoundException`, `ItemNotAvailableException`, `BorrowLimitExceededException` (extend `LibraryException`)
- **Repository<T, ID> (Interface) & InMemoryRepository<T, ID> (Class):** Generic storage interface and HashMap implementation with an ID extractor function.
- **SearchCache (Class):** LRU cache storing the last 5 searched titles using `LinkedHashMap` with `removeEldestEntry`.
- **CollectionUtils (Class):** Utility methods `maxOf()` using comparable and `totalFees()` using bounded wildcard `List<? extends LibraryItem>`.
- **LibraryService (Class):** Handles library business rules, item/member operations, ISBN validation, waitlist queues (`Map<Integer, Queue<Member>>` with `ArrayDeque`), sorting, statistics, and text file report export.

---

## 3. Test Cases

### 1. Successful Borrow
- **Input:** Choice `3`, Member id: `1`, Item id: `1001`
- **Output:** `Issued: Book[1001, Clean Code, 2008, Robert Martin]`

### 2. Borrowing Unavailable Item
- **Input:** Choice `3`, Member id: `2`, Item id: `1001`
- **Output:** `ERROR: Item 1001 is not available. Added to waitlist? (y/n): y` -> `Added to waitlist.`

### 3. Exceeding Borrow Limit
- **Input:** Choice `3`, Member tries to borrow a 4th item when 3 items are already borrowed.
- **Output:** `ERROR: Member 1 reached borrow limit`

### 4. Invalid Item ID
- **Input:** Choice `3`, Member id: `1`, Item id: `9999`
- **Output:** `ERROR: Item 9999 not found`

### 5. Waitlist Notification on Return
- **Input:** Choice `4`, Item id: `1001`, Late by: `3`
- **Output:**
  ```text
  Late fee: 6.0
  Notification: Member 2 (Ravi) is next in the waitlist for 1001.
  ```

---

## 4. LRU Cache and Memory Leaks
If we keep storing search keywords in an unbounded static HashMap, those entries will remain referenced by the class throughout the application's lifecycle. Because static fields are GC roots, the garbage collector can never reclaim this memory, causing a memory leak that can eventually crash the application with an `OutOfMemoryError`. An LRU cache using `LinkedHashMap` with `removeEldestEntry` fixes this by capping the size to 5 and evicting the oldest entries when new ones are added.

---

## 5. Challenges Faced and Learnings
- Learned how to avoid `ConcurrentModificationException` by using `Iterator.remove()` when removing old items from collections.
- Learned how to write bounded wildcards like `List<? extends LibraryItem>` so utility methods can accept lists of subclasses.
- Practiced using `ArrayDeque` inside a `Map` to manage waiting queues for borrowed items.
- Understood why getters should return unmodifiable collections using `Collections.unmodifiableList` to keep data secure.
