create table if not exists categories
(
    id    bigserial primary key,
    title varchar(150) not null
);

create table if not exists properties
(
    id    bigserial primary key,
    title varchar(150) not null
);

create table if not exists items
(
    id          bigserial primary key,
    title       varchar(150) not null,
    description varchar(500)
);

create table if not exists items_properties
(
    id             bigserial primary key,
    item_id        bigint references items (id)      not null,
    property_id    bigint references properties (id) not null,
    property_value varchar(250)                      not null
);

create index if not exists idx_items_properties_item_id on items_properties (item_id);
create index if not exists idx_items_properties_property_id on items_properties (property_id);

create table if not exists items_categories
(
    id                 bigserial primary key,
    item_id            bigint references items (id)      not null,
    parent_category_id bigint references categories (id),
    category_id        bigint references categories (id) not null,
    category_value     varchar(250)                      not null
);

create index if not exists idx_items_categories_item_id on items_categories (item_id);
create index if not exists idx_items_categories_category_id on items_categories (category_id);
create index if not exists idx_items_categories_parent_category_id on items_categories (parent_category_id)
    where not parent_category_id is null;

create table if not exists schemas
(
    id          bigserial primary key,
    title       varchar(150) not null,
    description varchar(500)
);

create table if not exists schemas_items
(
    id        bigserial primary key,
    schema_id bigint references schemas (id) not null,
    item_id   bigint references items (id)   not null
);

create index if not exists idx_schemas_items_schema_id on schemas_items (schema_id);
create index if not exists idx_schemas_items_item_id on schemas_items (item_id);

