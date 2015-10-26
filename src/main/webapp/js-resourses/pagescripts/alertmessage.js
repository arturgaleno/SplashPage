$(function(){
	
	App = window.App || { };
	
	App.AlertMessage = Backbone.Model.extend({
		defaults: {
			message: null,
			types: {"success": "alert alert-success", "error": "alert alert-danger", "info": "alert alert-info"},
			defaultType: "alert-info",
			selectedType: null,
		    parentViewSelector : null
		}
	});

	App.AlertMessageView = Backbone.View.extend({
		tagName: 'div',
	    className: 'alert',
	    id: 'errorMsg',
	  
	    initialize: function(model) {
	        _.bindAll(this, 'render');

	        this.template = $('#errorMsg_template').html();
	        
	        this.role = 'alert';
	        
	        this.model.on("change", this.render);
	        
	        if (this.model.has("message")) 
	        	this.render();
	    },

	    render: function() {
	        var rendered = _.template(this.template);
	        this.$el.html(rendered({"message" : this.model.get("message")}));
	        
	        this.$el.attr('role', this.role);
	        
	        var types = this.model.get("types");
	        
	        if (this.model.has("selectedType")) {
	        	this.$el.attr("class", _.property(this.model.get("selectedType"))(types));	        	
	        } else {
	        	this.$el.attr("class", _.property(this.model.get("defaultType"))(types));
	        }
	        
	        if (this.model.has("parentViewSelector")) 
	        	$(this.model.get("parentViewSelector")).append(this.el);
	    }
	});
});
