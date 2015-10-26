$(function(){
	
	var App = window.App || { };
	
	var validEmailRegex = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
	
	App.EmailSubmit = Backbone.Model.extend({
		urlRoot: "/SplashPage/emailSubmit",
		defaults: {
		    email: null
		},
		validate: function(attrs, options) {
			
			var regex = new RegExp(validEmailRegex);
			
			if (!regex.test(attrs.email)) {
				return "Invalid email!";
			}
			
		}
	});

	App.EmailSubmitView = Backbone.View.extend({
		tagName: 'form',
	    className: 'align_middle',
	    id: 'email_form',
	    
	    events: {
	        'submit' : 'saveEmail',
	        'blur #inputEmail': 'emailChanged'
	    },

	    initialize: function(model) {
	        _.bindAll(this, 'render', 'emailChanged', 'saveEmail', 'showError', 'invalidModel', 'showSuccess');

	        this.template = $('#form_template').html();
	        this.model = new App.EmailSubmit();
	        
	        this.messageModel = new window.App.AlertMessage();
	        this.messageModel.set("parentViewSelector","div.form_container.centered");
	        this.messageView = new App.AlertMessageView({model: this.messageModel});
	        
	        this.model.on("invalid", this.invalidModel);
	        
	        this.render();
	    },

	    render: function() {
	        var rendered = _.template(this.template);
	        this.$el.html(rendered);

	        this.inputEmail = this.$el.find('#inputEmail');

	        $('.form').append(this.el);
	    },
	    
	    emailChanged: function(e) {
	    	var emailValue = this.inputEmail.val();
	        this.model.set({email: emailValue});
	    },

	    saveEmail: function(e) {
	    	e.preventDefault();
	        this.model.save(null, {dataType: 'text', success: this.showSuccess, error: this.showError});
	    },

	    showError:function(model, response, options) {
	    	this.messageModel.set({message : "Error when execute request. Please try again later.", selectedType : "error"});
	    },
	    
	    showSuccess:function(model, response, options) {
	    	this.messageModel.set({message : "Please, check you email, we'll send a message for you!", selectedType : "success"});
	    	this.model.clear();
	    	this.inputEmail.val("");
	    },
	    
	    invalidModel:function(model, error) {
	    	this.messageModel.set({message : model.validationError, selectedType : "error"});
	    }
	});
	
	var emailSubmitView = new App.EmailSubmitView();
});
