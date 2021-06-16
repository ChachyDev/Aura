package club.chachy.selenium.utils.http.github

import com.google.gson.annotations.SerializedName

data class GithubRelease(@SerializedName("assets_url") val assets: String)