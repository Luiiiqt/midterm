const apiBase = "http://localhost:1111/api/products"

document.addEventListener("DOMContentLoaded", fetchProducts)

function fetchProducts() {
    fetch(apiBase)
        .then(res => res.json())
        .then(cars => {
            const body = document.getElementById("productTableBody")
            body.innerHTML = ""
            var counter = 0;
            products.forEach(product => {
                body.innerHTML += `
              <tr class="text-center">
                <td class="border p-2">${++counter}</td>
                <td class="border p-2">${product.name}</td>
                <td class="border p-2">${product.description}</td>
                <td class="border p-2">${product.stock}</td>
                <td class="border p-2">${product.unit}</td>
                <td class="border p-2">${product.price}</td>
                <td class="border p-2">
                  <button onclick="openEditModal(${product.id}, '${product.name}', '${product.description}', ${product.stock}, '${product.unit}', '${product.price}')" class="bg-yellow-500 text-white px-3 py-1 rounded hover:bg-yellow-600">Edit</button>
                  <button onclick="deleteCar(${product.id})" class="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600">Delete</button>
                </td>
              </tr>`
            })
        })
        .catch(err => console.error(err))
}

function openCreateModal() {
    document.getElementById("productForm").reset()
    document.getElementById("productId").value = ""
    document.getElementById("modalTitle").innerText = "Add Product"
    document.getElementById("productModal").classList.remove("hidden")
}

function openEditModal(id, name, description, stock, unit, price) {
    document.getElementById("productId").value = id
    document.getElementById("productName").value = name
    document.getElementById("productDescription").value = description
    document.getElementById("productStock").value = stock
    document.getElementById("producUnit").value = unit
    document.getElementById("productPrice").value = price
    document.getElementById("modalTitle").innerText = "Edit Product"
    document.getElementById("productModal").classList.remove("hidden")
}

function closeModal() {
    document.getElementById("productModal").classList.add("hidden")
}

function saveProduct(e) {
    e.preventDefault()
    const id = document.getElementById("productId").value
    const name = document.getElementById("productName").value
    const description = document.getElementById("productDescription").value
    const stock = document.getElementById("productStock").value
    const unit = document.getElementById("productUnit").value
    const price = document.getElementById("productPrice").value

    const car = { name, description, stock, unit, price }
    const method = id ? "PUT" : "POST"
    const url = id ? `${apiBase}/${id}` : apiBase

    fetch(url, {
        method,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(car)
    })
        .then(res => res.json())
        .then(() => {
            closeModal()
            fetchCars()
        })
        .catch(err => console.error(err))
}

function deleteCar(id) {
    if (!confirm("Delete this product?")) return
    fetch(`${apiBase}/${id}`, { method: "DELETE" })
        .then(() => fetchCars())
        .catch(err => console.error(err))
}
