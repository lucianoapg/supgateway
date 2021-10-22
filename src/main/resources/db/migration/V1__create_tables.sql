create table product (
                        id bigint not null auto_increment, 
                        
                        name varchar(255), 
                        quantity_in_stock integer, 
                        unit_price decimal(19,2),
                        supplier_id bigint, 
                        created_at datetime(6), 
                        updated_at datetime(6),                      
                        primary key (id)
                    ) engine=InnoDB;
create table supplier (
                        id bigint not null auto_increment,
                         
                        name varchar(255),
                        created_at datetime(6), 
                        updated_at datetime(6), 
                        primary key (id)
                    ) engine=InnoDB;
alter table product add constraint fk_supplier foreign key (supplier_id) references supplier (id);