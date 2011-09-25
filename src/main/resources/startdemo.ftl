<html>
<body>

<h2>Place an order</h2>
<hr>
<form action="complete" method="POST" enctype="multipart/form-data">

	<p>Customer ID:
	<input type="text" name="customerId" />
	<br />
	
	<p>Product:
	<select name="productId">
		<option value="A">1 Bag of Apples: $25.00</option>
		<option value="B">2 Bags of Apples: $50.00</option>
	</select>
	
	<p>Payment method:
	<select name="paymentMethod">
		<option value="creditCard">Credit Card</option>
		<option value="account">Account</option>
	</select>
	
	<p>Shipping method:
	<select name="shippingMethod">
		<option value="ground">Standard (Ground)</option>
		<option value="expedited">Expedited (Next-Day Air)</option>
	</select>
	
	<input type="submit" value="Complete">
	
</form>

</body>
</html>
