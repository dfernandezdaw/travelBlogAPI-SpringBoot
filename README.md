
# Travel Blog Spring Boot API

This is a school proyect to learn Spring Boot, developing a REST API for a travel blog.




## API Reference

All endpoints are secured, you have to authenticate with httpBasic on endpoint /token to get your JWT

#### Get token

```http
  POST /token
```

#### Get all travels

```http
  GET /api/travels
```

#### Get one travel

```http
  GET /api/travels/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of travel to fetch |

#### Create one travel

```http
  POST /api/travels
```

#### Update one travel

```http
  PUT /api/travels/{id}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of travel to update |
  
#### Update one travel

```http
  DELETE /api/travels/{id}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of travel to delete |

#### Get all comments

```http
  GET /api/comments
```
#### Get all comments in a travel

```http
  GET /api/travels/{id}/comments
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of travel to retrieve it comments |

#### Create one comment

```http
  POST /api/travels/{id}/comments
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of travel in which comment is created |

#### Update one comment

```http
  PUT /api/travel/{id}/comments/{id_comment}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of travel |
| `id_comment` | `string` | **Required** Id of comment |

#### Delete one comment

```http
  DELETE /api/travel/{id}/comments/{id_comment}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of travel |
| `id_comment` | `string` | **Required** Id of comment |

#### Get all categories

```http
  GET /api/categories
```
#### Create one category

```http
  POST /api/categories
```
#### Get one categories

```http
  GET /api/categories/{id}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of category |

#### Update one category

```http
  PUT /api/categories/{id}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of category to update |

#### Delete one category

```http
  DELETE /api/categories/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of category to delete |

#### Create one user

```http
  POST /api/users
```
#### Delete one user

```http
  DELETE /api/users/{id}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of user to delete |

## Authors

- [@dfernandezdaw](https://www.github.com/dfernandezdaw)

