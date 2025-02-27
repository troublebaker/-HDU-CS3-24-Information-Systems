# Course Database

## 数据库信息

- **数据库名**: course
- **数据库类型**: PostgreSQL
- **用户名**: postgres
- **密码**: 1234

## Java 和 Spring Boot 信息

- **Java 版本**: 17
- **Spring Boot 版本**: 3.4.3
- IntelliJ IDEA 2024.3.3

## 表结构

### 表: courses

该表用于存储课程信息。

| 列名       | 数据类型               | 约束条件                             |
|------------|------------------------|--------------------------------------|
| id         | integer                | NOT NULL, PRIMARY KEY                |
| name       | character varying(100)  | NOT NULL                             |
| description| text                   | NOT NULL                             |
| duration   | integer                | NOT NULL                             |
| image      | character varying(255)  |                                      |
| status     | integer                |                                      |

### SQL 创建语句


CREATE TABLE IF NOT EXISTS public.courses  
(  
    id integer NOT NULL DEFAULT nextval('courses_id_seq'::regclass),  
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,  
    description text COLLATE pg_catalog."default" NOT NULL,  
    duration integer NOT NULL,  
    image character varying(255) COLLATE pg_catalog."default",  
    status integer,  
    CONSTRAINT courses_pkey PRIMARY KEY (id)  
);

### 表: users  

该表用于存储用户信息。  

| 列名       | 数据类型               | 约束条件                             |
|------------|------------------------|--------------------------------------|
| id         | integer                | NOT NULL, PRIMARY KEY                |
| name       | character varying(100)  | NOT NULL                             |
| password   | character varying(255)  | NOT NULL                             |
| role       | character varying(20)   | NOT NULL, CHECK (role IN ('学生', '教师', '管理员')) |
| login      | integer                | NOT NULL DEFAULT 0                   |
| status     | integer                |                                      |

#### SQL 创建语句  

```sql  
CREATE TABLE IF NOT EXISTS public.users  
(  
    id integer NOT NULL DEFAULT nextval('users_id_seq'::regclass),  
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,  
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,  
    role character varying(20) COLLATE pg_catalog."default" NOT NULL,  
    login integer NOT NULL DEFAULT 0,  
    status integer,  
    CONSTRAINT users_pkey PRIMARY KEY (id),  
    CONSTRAINT users_role_check CHECK (role::text = ANY (ARRAY['学生'::character varying, '教师'::character varying, '管理员'::character varying]::text[]))  
);
```
