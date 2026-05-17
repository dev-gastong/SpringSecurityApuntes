# Posibles Ejemplos para Permisos

## Permisos para el Catálogo de Productos
---
- PermissionEntity readProd = PermissionEntity.builder().name("PRODUCTOS_LEER").build();
- PermissionEntity createProd = PermissionEntity.builder().name("PRODUCTOS_CREAR").build();
- PermissionEntity updateProd = PermissionEntity.builder().name("PRODUCTOS_EDITAR").build();
---
## Permisos para las Órdenes de Compra (Pedidos)
- PermissionEntity createOrder = PermissionEntity.builder().name("ORDENES_CREAR").build();
- PermissionEntity readOrderOwn = PermissionEntity.builder().name("ORDENES_LEER_PROPIAS").build();
- PermissionEntity readOrderAll = PermissionEntity.builder().name("ORDENES_LEER_TODAS").build();
- PermissionEntity cancelOrder = PermissionEntity.builder().name("ORDENES_CANCELAR").build();
---
## Permisos de Administración / Soporte
- PermissionEntity BanUser = PermissionEntity.builder().name("USUARIOS_BLOQUEAR").build();
- PermissionEntity viewMetrics = PermissionEntity.builder().name("REPORTES_VER_METRICAS").build();