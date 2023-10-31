package com.example.b2012051_tranvantruong_uocmo.apis

import com.example.b2012051_tranvantruong_uocmo.constants.ApiConstants.USER_LOGIN
import com.example.b2012051_tranvantruong_uocmo.constants.ApiConstants.USER_REGISTER
import com.example.b2012051_tranvantruong_uocmo.constants.ApiConstants.WISHES
import com.example.b2012051_tranvantruong_uocmo.models.RequestAddWish
import com.example.b2012051_tranvantruong_uocmo.models.RequestDeleteWish
import com.example.b2012051_tranvantruong_uocmo.models.RequestRegisterOrLogin
import com.example.b2012051_tranvantruong_uocmo.models.RequestUpdateWish
import com.example.b2012051_tranvantruong_uocmo.models.ResponseMessage
import com.example.b2012051_tranvantruong_uocmo.models.UserResponse
import com.example.b2012051_tranvantruong_uocmo.models.Wish
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.PATCH
import retrofit2.http.POST

interface ApiService {
    @POST(USER_REGISTER)
    suspend fun registerUser(@Body request: RequestRegisterOrLogin): Response<UserResponse>

    @POST(USER_LOGIN)
    suspend fun loginUser(@Body request: RequestRegisterOrLogin): Response<UserResponse>

    @GET(WISHES)
    suspend fun getWishList(): Response<List<Wish>>

    @POST(WISHES)
    suspend fun addWish(@Body addWish: RequestAddWish): Response<ResponseMessage>

    @PATCH(WISHES)
    suspend fun updateWish(@Body updateWish: RequestUpdateWish): Response<ResponseMessage>

    @HTTP(method = "DELETE", path = WISHES, hasBody = true)
    suspend fun deleteWish(@Body deleteWish: RequestDeleteWish) : Response<ResponseMessage>
}