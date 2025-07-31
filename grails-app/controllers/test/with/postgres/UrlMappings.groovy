package test.with.postgres

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/auth/login"(controller: 'auth', action: 'login', method: 'POST')
        "/users"(controller: 'user', action: 'index', method: 'GET')
        "/users"(controller: 'user', action: 'save', method: 'POST')

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
