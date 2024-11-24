package com.example.soap

import org.springframework.ws.server.endpoint.annotation.Endpoint
import org.springframework.ws.server.endpoint.annotation.PayloadRoot
import org.springframework.ws.server.endpoint.annotation.RequestPayload
import org.springframework.ws.server.endpoint.annotation.ResponsePayload
import jakarta.xml.bind.annotation.XmlElement
import jakarta.xml.bind.annotation.XmlRootElement
import jakarta.xml.bind.annotation.XmlAccessorType
import jakarta.xml.bind.annotation.XmlAccessType

@Endpoint
class SimpleServiceEndpoint {

    @PayloadRoot(namespace = "http://example.com/SimpleService", localPart = "AddRequest")
    @ResponsePayload
    fun add(@RequestPayload request: AddRequest): AddResponse {
        val response = AddResponse()
        response.result = request.a + request.b
        return response
    }
}

@XmlRootElement(name = "AddRequest", namespace = "http://example.com/SimpleService")
@XmlAccessorType(XmlAccessType.FIELD)
data class AddRequest(
        @field:XmlElement(name = "a", required = true)
        var a: Int = 0,

        @field:XmlElement(name = "b", required = true)
        var b: Int = 0
)

@XmlRootElement(name = "AddResponse", namespace = "http://example.com/SimpleService")
@XmlAccessorType(XmlAccessType.FIELD)
data class AddResponse(
        @field:XmlElement(name = "result", required = true)
        var result: Int = 0
)