
# Travel Blog Spring Boot API

This is a school proyect to learn Spring Boot, developing a REST API for a travel blog.




## API Reference

All endpoints are secured, you have to authenticate with httpBasic on endpoint /token to get your JWT

#### Get token

```
  POST /token
```

#### Get all travels

```
  GET /api/travels
```

#### Get one travel

```
  GET /api/travels/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of travel to fetch |

#### Create one travel

```
  POST /api/travels
```

#### Update one travel

```
  PUT /api/travels/{id}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of travel to update |
  
#### Update one travel

```
  DELETE /api/travels/{id}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of travel to delete |

#### Get all comments

```
  GET /api/comments
```
#### Get all comments in a travel

```
  GET /api/travels/{id}/comments
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of travel to retrieve it comments |

#### Create one comment

```
  POST /api/travels/{id}/comments
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of travel in which comment is created |

#### Update one comment

```
  PUT /api/travel/{id}/comments/{id_comment}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of travel |
| `id_comment` | `string` | **Required** Id of comment |

#### Delete one comment

```
  DELETE /api/travel/{id}/comments/{id_comment}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of travel |
| `id_comment` | `string` | **Required** Id of comment |

#### Get all categories

```
  GET /api/categories
```
#### Create one category

```
  POST /api/categories
```
#### Get one categories

```
  GET /api/categories/{id}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of category |

#### Update one category

```
  PUT /api/categories/{id}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of category to update |

#### Delete one category

```
  DELETE /api/categories/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of category to delete |

#### Create one user

```
  POST /api/users
```
#### Delete one user

```
  DELETE /api/users/{id}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of user to delete |

## Authors

- [@dfernandezdaw](https://www.github.com/dfernandezdaw)

