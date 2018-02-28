$(function() {
    
    console.log("Hola java!");
    
    var modal = $("#dialogoModal");
    
    var hrefIni = "pelicula?action=eliminarPelicula&pkID=id_pel", hrefReplace;
    
    $('.abreDialogo').click(function(e){
        
        
        var button = $(e.currentTarget)        
        
        var recipient = button.data('idpelicula');
        var nombrePelicula = button.data('nompelicula');

        modal.find('.modal-title').text('Eliminar pelicula ' + nombrePelicula+'?');
        
        hrefReplace = hrefIni.replace("id_pel",recipient);
        
        modal.find('.btn-danger')[0].href = hrefReplace;
        
        console.log(hrefReplace);       
        
    })
});


