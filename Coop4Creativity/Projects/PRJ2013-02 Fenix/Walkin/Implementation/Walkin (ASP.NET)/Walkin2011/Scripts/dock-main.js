$(document).ready(function () {

    /* main navigation. */
    $('#dock').Fisheye(
	  {
	      maxWidth: 15,
	      items: 'a',
	      itemsText: 'span',
	      container: '.dock-container',
	      itemWidth: 40,
	      proximity: 32,
	      alignment: 'left',
	      valign: 'bottom',
	      halign: 'left'
	  }
	);
})
