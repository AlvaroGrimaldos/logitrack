<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Productos - LogiTrack</title>
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/productos.css">
</head>
<body>
    <header class="header">
        <div class="container">
            <div class="navbar">
                <div class="logo">LogiTrack</div>
                <nav class="nav-links">
                    <a href="dashboard.html" class="nav-link">Dashboard</a>
                    <a href="movimientos.html" class="nav-link">Movimientos</a>
                    <a href="bodegas.html" class="nav-link">Bodegas</a>
                    <a href="productos.html" class="nav-link active">Productos</a>
                    <a href="auditoria.html" class="nav-link">Auditoría</a>
                </nav>
                <div class="user-menu">
                    <span id="userName">Usuario</span>
                    <button onclick="logout()" class="btn btn-secondary">Cerrar Sesión</button>
                </div>
            </div>
        </div>
    </header>

    <main class="container">
        <div class="page-header">
            <h1>Gestión de Productos</h1>
            <p>Administrar catálogo de productos</p>
            <button class="btn btn-primary" onclick="showProductModal('create')">+ Nuevo Producto</button>
        </div>

        <div class="filters">
            <div class="filter-group">
                <label for="searchProducto">Buscar</label>
                <input type="text" id="searchProducto" placeholder="Nombre o categoría..." onkeyup="filterProductos()">
            </div>
            <div class="filter-group">
                <label for="filterCategoria">Categoría</label>
                <select id="filterCategoria" onchange="filterProductos()">
                    <option value="">Todas</option>
                    <option value="TECNOLOGIA">Tecnología</option>
                    <option value="MUEBLES">Muebles</option>
                    <option value="OFICINA">Oficina</option>
                    <option value="HERRAMIENTAS">Herramientas</option>
                </select>
            </div>
            <div class="filter-group">
                <label for="filterActivo">Estado</label>
                <select id="filterActivo" onchange="filterProductos()">
                    <option value="">Todos</option>
                    <option value="true">Activos</option>
                    <option value="false">Inactivos</option>
                </select>
            </div>
        </div>

        <div id="productosList" class="productos-table-container">
            <div class="loading">Cargando productos...</div>
        </div>
    </main>

    <!-- Modal Producto -->
    <div id="productoModal" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <h3 id="productoModalTitle">Nuevo Producto</h3>
                <span class="close" onclick="closeProductModal()">&times;</span>
            </div>
            <form id="productoForm">
                <div class="form-group">
                    <label for="productoNombre">Nombre *</label>
                    <input type="text" id="productoNombre" name="nombre" required>
                </div>
                <div class="form-group">
                    <label for="productoCategoria">Categoría *</label>
                    <select id="productoCategoria" name="categoria" required>
                        <option value="">Seleccionar categoría...</option>
                        <option value="TECNOLOGIA">Tecnología</option>
                        <option value="MUEBLES">Muebles</option>
                        <option value="OFICINA">Oficina</option>
                        <option value="HERRAMIENTAS">Herramientas</option>
                        <option value="ELECTRODOMESTICOS">Electrodomésticos</option>
                        <option value="MATERIALES">Materiales</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="productoPrecio">Precio *</label>
                    <input type="number" id="productoPrecio" name="precio" step="0.01" min="0" required>
                </div>
                <div class="form-group">
                    <label>
                        <input type="checkbox" id="productoActivo" name="activo" checked>
                        Producto activo
                    </label>
                </div>
                <div class="form-actions">
                    <button type="button" class="btn btn-secondary" onclick="closeProductModal()">Cancelar</button>
                    <button type="submit" class="btn btn-primary">Guardar Producto</button>
                </div>
            </form>
        </div>
    </div>

    <script src="js/config.js"></script>
    <script src="js/auth.js"></script>
    <script src="js/productos.js"></script>
</body>
</html>