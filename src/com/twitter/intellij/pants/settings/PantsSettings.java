package com.twitter.intellij.pants.settings;

import com.intellij.openapi.components.*;
import com.intellij.openapi.externalSystem.settings.AbstractExternalSystemSettings;
import com.intellij.openapi.externalSystem.settings.ExternalSystemSettingsListener;
import com.intellij.openapi.project.Project;
import com.intellij.util.containers.ContainerUtilRt;
import com.intellij.util.xmlb.annotations.AbstractCollection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

/**
 * Created by fedorkorotkov
 */
@State(
  name = "PantsSettings",
  storages = {
    @Storage(file = StoragePathMacros.PROJECT_FILE),
    @Storage(file = StoragePathMacros.PROJECT_CONFIG_DIR + "/pants.xml", scheme = StorageScheme.DIRECTORY_BASED)
  }
)
public class PantsSettings extends AbstractExternalSystemSettings<PantsSettings, PantsProjectSettings, PantsSettingsListener>
  implements PersistentStateComponent<PantsSettings.MyState> {

  public PantsSettings(@NotNull Project project) {
    super(PantsSettingsListener.TOPIC, project);
  }

  @NotNull
  public static PantsSettings getInstance(@NotNull Project project) {
    return ServiceManager.getService(project, PantsSettings.class);
  }

  @Override
  public void subscribe(@NotNull ExternalSystemSettingsListener<PantsProjectSettings> listener) {

  }

  @Override
  protected void copyExtraSettingsFrom(@NotNull PantsSettings settings) {

  }

  @Override
  protected void checkSettings(@NotNull PantsProjectSettings old, @NotNull PantsProjectSettings current) {

  }

  @SuppressWarnings("unchecked")
  @Nullable
  @Override
  public MyState getState() {
    final MyState state = new MyState();
    fillState(state);
    return state;
  }

  @Override
  public void loadState(MyState state) {
    super.loadState(state);
  }

  public static class MyState implements State<PantsProjectSettings> {
    Set<PantsProjectSettings> myLinkedExternalProjectsSettings = ContainerUtilRt.newTreeSet();

    @AbstractCollection(surroundWithTag = false, elementTypes = {PantsProjectSettings.class})
    public Set<PantsProjectSettings> getLinkedExternalProjectsSettings() {
      return myLinkedExternalProjectsSettings;
    }

    public void setLinkedExternalProjectsSettings(Set<PantsProjectSettings> settings) {
      if (settings != null) {
        myLinkedExternalProjectsSettings.addAll(settings);
      }
    }
  }
}
