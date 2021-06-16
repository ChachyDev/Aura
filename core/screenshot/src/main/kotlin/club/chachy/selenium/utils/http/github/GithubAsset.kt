package club.chachy.selenium.utils.http.github

import com.google.gson.annotations.SerializedName

data class GithubAsset(val name: String, @SerializedName("browser_download_url") val download: String)