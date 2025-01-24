window.onload=function(){
	 var maxCards = 5; // Maximum number of cards to display
    var cardGroup = document.querySelector('.card-group'); // Select the card group
    var cards = cardGroup.getElementsByClassName('card'); // Get all cards
	
	    // Loop through cards
	    for (var i = 0; i < cards.length; i++) {
	        if (i % maxCards == 0) {
	      		//create a new row
	      		var newRow = document.createElement('div');
	      		newRow.className = 'row';
	      		cardGroup.appendChild(newRow);
	        }
	        
            newRow.appendChild(cards[i]);
			newRow.classList.add('card-group');
	    }

};