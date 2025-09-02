
# Task Management API

A simple Task Management API built with Gradle.

---

## 🚀 Setup Instructions

Follow the steps below to set up and run the project locally:

### 1. Clone the Repository
```bash
git clone https://github.com/siddharth1201/airtribeTaskManagement.git
````

### 2. Navigate to the Project Directory

```bash
cd AIRTRIBETASKMANAGEMENT
```

### 3. Run the Application

```bash
./gradlew run
```
---

## 📌 Requirements

* Java 17+ (or your project’s required version)
* Gradle (Wrapper already included)

---

## ⚡ Usage

Once the application is running, you can interact with the API endpoints using:

* **cURL**
* **Postman**
* **Any HTTP client**



# 📘 Task Management API Documentation

## Base URL

```
/tasks
```



## 🔹 **Get Tasks**

### `GET /tasks`

Retrieve all tasks or a single task by ID.

#### Query Parameters

| Name | Type   | Required | Description                                                  |
| ---- | ------ | -------- | ------------------------------------------------------------ |
| `id` | string | No       | If provided, fetches the task by ID. Must be a valid number. |

#### Responses

* **200 OK**

  * Returns a list of tasks (`TaskResponseDTO[]`) when no `id` is provided.
  * Returns a single task (`TaskResponseDTO`) when `id` is provided.
* **400 Bad Request**

  * Returned if `id` is not a valid number.
* **404 Not Found**

  * Returned if the task with the given `id` does not exist.

#### Example Response (200 – list of tasks)

```json
[
  {
    "id": 1,
    "name": "Complete API docs",
    "description": "Write complete official API documentation for TaskController",
    "status": "IN_PROGRESS",
    "priority": "HIGH",
    "dueDate": "2025-09-10T12:00:00.000+00:00"
  },
  {
    "id": 2,
    "name": "Review PR",
    "description": "Review the pull request for bug fixes",
    "status": "TODO",
    "priority": "MEDIUM",
    "dueDate": "2025-09-12T12:00:00.000+00:00"
  }
]
```

---

## 🔹 **Create Task**

### `POST /tasks`

Create a new task.

#### Request Body (JSON – `TaskRequestDTO`)

| Field         | Type   | Required | Validation Rules                      |
| ------------- | ------ | -------- | ------------------------------------- |
| `name`        | string | Yes      | Cannot be blank                       |
| `description` | string | Yes      | Between 20 and 500 characters         |
| `status`      | enum   | Yes      | One of: `TODO`, `IN_PROGRESS`, `DONE` |
| `priority`    | enum   | Yes      | One of: `LOW`, `MEDIUM`, `HIGH`       |
| `dueDate`     | date   | Yes      | Must be in the future                 |

#### Example Request

```json
{
  "name": "Write API docs",
  "description": "Prepare detailed API documentation for the TaskController endpoints",
  "status": "TODO",
  "priority": "HIGH",
  "dueDate": "2025-09-15T12:00:00.000+00:00"
}
```

#### Responses

* **201 Created** → Returns the created task (`TaskResponseDTO`).
* **400 Bad Request** → Invalid input, validation errors.

#### Example Response (201)

```json
{
  "id": 3,
  "name": "Write API docs",
  "description": "Prepare detailed API documentation for the TaskController endpoints",
  "status": "TODO",
  "priority": "HIGH",
  "dueDate": "2025-09-15T12:00:00.000+00:00"
}
```

---

## 🔹 **Update Task**

### `PUT /tasks/{id}`

Update an existing task.

#### Path Parameters

| Name | Type | Required | Description              |
| ---- | ---- | -------- | ------------------------ |
| `id` | long | Yes      | ID of the task to update |

#### Request Body (JSON – `TaskRequestDTO`)

(Same structure as **Create Task**)

#### Example Request

```json
{
  "name": "Update API docs",
  "description": "Modify the existing documentation to include validation rules",
  "status": "IN_PROGRESS",
  "priority": "MEDIUM",
  "dueDate": "2025-09-20T12:00:00.000+00:00"
}
```

#### Responses

* **200 OK** → Returns the updated task (`TaskResponseDTO`).
* **400 Bad Request** → Invalid request or validation error.
* **404 Not Found** → Task with given `id` not found.

#### Example Response (200)

```json
{
  "id": 3,
  "name": "Update API docs",
  "description": "Modify the existing documentation to include validation rules",
  "status": "IN_PROGRESS",
  "priority": "MEDIUM",
  "dueDate": "2025-09-20T12:00:00.000+00:00"
}
```

---

## 🔹 **Delete Task**

### `DELETE /tasks/{id}`

Delete a task by ID.

#### Path Parameters

| Name | Type | Required | Description              |
| ---- | ---- | -------- | ------------------------ |
| `id` | long | Yes      | ID of the task to delete |

#### Responses

* **204 No Content** → Task deleted successfully.
* **404 Not Found** → Task with given `id` not found.

---

# 📑 Data Models

### TaskRequestDTO

```json
{
  "name": "string (required)",
  "description": "string (20-500 chars, required)",
  "status": "TODO | IN_PROGRESS | DONE (required)",
  "priority": "LOW | MEDIUM | HIGH (required)",
  "dueDate": "ISO8601 date (must be in the future, required)"
}
```

### TaskResponseDTO

```json
{
  "id": "long",
  "name": "string",
  "description": "string",
  "status": "TODO | IN_PROGRESS | DONE",
  "priority": "LOW | MEDIUM | HIGH",
  "dueDate": "ISO8601 date"
}
```


### POSTMAN Collection
- In your postman import the file 

```
AirtribeTaskManager.postman_collection.json
```
- Run the collection and test cases.