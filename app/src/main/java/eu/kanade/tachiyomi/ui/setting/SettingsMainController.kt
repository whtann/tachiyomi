package eu.kanade.tachiyomi.ui.setting

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.preference.PreferenceScreen
import eu.kanade.tachiyomi.R
import eu.kanade.tachiyomi.ui.base.controller.pushController
import eu.kanade.tachiyomi.ui.setting.search.SettingsSearchController
import eu.kanade.tachiyomi.util.preference.iconRes
import eu.kanade.tachiyomi.util.preference.iconTint
import eu.kanade.tachiyomi.util.preference.onClick
import eu.kanade.tachiyomi.util.preference.preference
import eu.kanade.tachiyomi.util.preference.titleRes
import eu.kanade.tachiyomi.util.system.getResourceColor

class SettingsMainController : SettingsController() {

    override fun setupPreferenceScreen(screen: PreferenceScreen) = screen.apply {
        titleRes = R.string.label_settings

        val tintColor = context.getResourceColor(R.attr.colorAccent)

        preference {
            iconRes = R.drawable.ic_tune_24dp
            iconTint = tintColor
            titleRes = R.string.pref_category_general
            onClick { router.pushController(SettingsGeneralController()) }
        }
        preference {
            iconRes = R.drawable.ic_palette_24dp
            iconTint = tintColor
            titleRes = R.string.pref_category_appearance
            onClick { router.pushController(SettingsAppearanceController()) }
        }
        preference {
            iconRes = R.drawable.ic_library_outline_24dp
            iconTint = tintColor
            titleRes = R.string.pref_category_library
            onClick { router.pushController(SettingsLibraryController()) }
        }
        preference {
            iconRes = R.drawable.ic_chrome_reader_mode_24dp
            iconTint = tintColor
            titleRes = R.string.pref_category_reader
            onClick { router.pushController(SettingsReaderController()) }
        }
        preference {
            iconRes = R.drawable.ic_get_app_24dp
            iconTint = tintColor
            titleRes = R.string.pref_category_downloads
            onClick { router.pushController(SettingsDownloadController()) }
        }
        preference {
            iconRes = R.drawable.ic_sync_24dp
            iconTint = tintColor
            titleRes = R.string.pref_category_tracking
            onClick { router.pushController(SettingsTrackingController()) }
        }
        preference {
            iconRes = R.drawable.ic_browse_outline_24dp
            iconTint = tintColor
            titleRes = R.string.browse
            onClick { router.pushController(SettingsBrowseController()) }
        }
        preference {
            iconRes = R.drawable.ic_settings_backup_restore_24dp
            iconTint = tintColor
            titleRes = R.string.label_backup
            onClick { router.pushController(SettingsBackupController()) }
        }
        preference {
            iconRes = R.drawable.ic_security_24dp
            iconTint = tintColor
            titleRes = R.string.pref_category_security
            onClick { router.pushController(SettingsSecurityController()) }
        }
        preference {
            iconRes = R.drawable.ic_code_24dp
            iconTint = tintColor
            titleRes = R.string.pref_category_advanced
            onClick { router.pushController(SettingsAdvancedController()) }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Inflate menu
        inflater.inflate(R.menu.settings_main, menu)

        // Initialize search option.
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.maxWidth = Int.MAX_VALUE

        // Change hint to show global search.
        searchView.queryHint = applicationContext?.getString(R.string.action_search_settings)

        searchItem.setOnActionExpandListener(
            object : MenuItem.OnActionExpandListener {
                override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                    preferences.lastSearchQuerySearchSettings().set("") // reset saved search query
                    router.pushController(SettingsSearchController())
                    return true
                }

                override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                    return true
                }
            },
        )
    }
}
