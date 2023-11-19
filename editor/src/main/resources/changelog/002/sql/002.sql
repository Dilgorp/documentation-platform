alter table categories
    add constraint categories_title_unique unique (title);
alter table properties
    add constraint properties_title_unique unique (title);