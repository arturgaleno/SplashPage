$(function(){
	
	//Aviso: skiped templating e dynamic render of survey to gain time
	
	var App = window.App || { };
	
	App.SurveyAnswer = Backbone.Model.extend({
		
		urlRoot: "/SplashPage/surveys",
		
		defaults: {
			answer1: null,
			answer2: null,
			answer3: null,
			email: null
		},
		
		validate: function(attrs, options) {
			if (attrs.answer1 == null) {
				return {msg: "Answer the question 1, please.", number: 0};
			}
			if (attrs.answer2 == null) {
				return {msg: "Answer the question 2, please.", number: 1};
			}
			if (attrs.answer3 == null) {
				return {msg: "Answer the question 3, please.", number: 2};
			}
		},
		
	    fecthByEmail: function(callback, callback2) {
	    	this.fetch({url: this.urlRoot + '/' + this.attributes.email,  
	    				dataType: 'text',
	    				success: function(model, response, options) {
	    					callback2();
	    				},
	    				error: function(model, response, options) {
	    					model.set({email: null});
	    					callback();
	    				}
	    		});
	    }
	});
	
	App.SurveyView = Backbone.View.extend({
		
		tagName: 'div',
	    className: 'form form-survey',
	    id: 'form_survey',
		
		events: {
			'change input[name="q1"]:checked' : 'q1AnswerChange',
			'change input[name="q2"]:checked' : 'q2AnswerChange',
			'change input[name="q3"]:checked' : 'q3AnswerChange',
			'click #btnSend' : 'send'
		},
		
		initialize: function(model) {
	        _.bindAll(this, 'render', 'q1AnswerChange', 
	        				'q2AnswerChange', 'q3AnswerChange', 
	        				'send', 'showError', 
	        				'invalidModel', 'showSuccess',
	        				'emailNotFound', 'emailFound');

	       
	        var url = window.location.href;
	        this.model = new App.SurveyAnswer({email: url.substr(url.lastIndexOf('/') + 1)});
	        
	        this.messageModel = new window.App.AlertMessage();
	        this.messageView = new App.AlertMessageView({model: this.messageModel});
	        
	        this.model.on("invalid", this.invalidModel);
	        
	        this.model.fecthByEmail(this.emailNotFound, this.emailFound);
	    },
	    
	    render: function() {
	    	var rendered = _.template(this.template);
	        this.$el.html(rendered);
	        
	        $('div.form_container.centered').append(this.el);
	    },
		
		q1AnswerChange: function() {
			this.model.set({answer1: $('input[name="q1"]:checked').val()});
		},
		
		q2AnswerChange: function() {
			this.model.set({answer2: $('input[name="q2"]:checked').val()});
		},
		
		q3AnswerChange: function() {
			this.model.set({answer3: $('input[name="q3"]:checked').val()});
		},
		
		send: function(e) {
	        this.model.save(null, {dataType: 'text', success: this.showSuccess, error: this.showError});
	    },

	    showError:function(model, response, options) {
	    	this.messageModel.set("parentViewSelector",".form_container.centered");
	    	if (response.status == 400) {
	    		this.messageModel.set({message : response.responseText, selectedType : "error"});
	    	} else {
	    		this.messageModel.set({message : "Error when execute request. Please try later.", selectedType : "error"});	    		
	    	}
	    },
	    
	    showSuccess:function(model, response, options) {
	    	this.messageModel.set("parentViewSelector",".form_container.centered");
	    	this.messageModel.set({message : "Thank you so much!", selectedType : "success"});
	    	this.model.clear();
	    },
	    
	    invalidModel:function(model, error) {
	    	this.messageModel.set("parentViewSelector",".question:eq("+model.validationError.number+")");
	    	this.messageModel.set({message : model.validationError.msg, selectedType : "error"});
	    },
	    
	    emailNotFound: function() {
	    	this.template = $('#email_notfound_template').html();
	    	this.render();
	    },
	    
	    emailFound: function() {
	    	this.template = $('#form_template').html();
	    	this.render();
	    }
	});
	
	var surveyView = new App.SurveyView();
});