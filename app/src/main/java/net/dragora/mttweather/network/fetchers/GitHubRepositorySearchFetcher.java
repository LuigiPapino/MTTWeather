package net.dragora.mttweather.network.fetchers;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import net.dragora.mttweather.data.stores.GitHubRepositorySearchStore;
import net.dragora.mttweather.data.stores.GitHubRepositoryStore;
import net.dragora.mttweather.network.GitHubService;
import net.dragora.mttweather.network.NetworkApi;
import net.dragora.mttweather.pojo.GitHubRepository;
import net.dragora.mttweather.pojo.GitHubRepositorySearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reark.reark.pojo.NetworkRequestStatus;
import io.reark.reark.utils.Log;
import io.reark.reark.utils.Preconditions;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by ttuo on 16/04/15.
 */
public class GitHubRepositorySearchFetcher extends AppFetcherBase {
    private static final String TAG = GitHubRepositorySearchFetcher.class.getSimpleName();

    private final GitHubRepositoryStore gitHubRepositoryStore;
    private final GitHubRepositorySearchStore gitHubRepositorySearchStore;

    public GitHubRepositorySearchFetcher(@NonNull NetworkApi networkApi,
                                         @NonNull Action1<NetworkRequestStatus> updateNetworkRequestStatus,
                                         @NonNull GitHubRepositoryStore gitHubRepositoryStore,
                                         @NonNull GitHubRepositorySearchStore gitHubRepositorySearchStore) {
        super(networkApi, updateNetworkRequestStatus);

        Preconditions.checkNotNull(gitHubRepositoryStore, "GitHub Repository Store cannot be null.");
        Preconditions.checkNotNull(gitHubRepositorySearchStore, ""
                                                                + "GitHub Repository Search Store cannot be null.");

        this.gitHubRepositoryStore = gitHubRepositoryStore;
        this.gitHubRepositorySearchStore = gitHubRepositorySearchStore;
    }

    @Override
    public void fetch(@NonNull Intent intent) {
        final String searchString = intent.getStringExtra("searchString");
        if (searchString != null) {
            fetchGitHubSearch(searchString);
        } else {
            Log.e(TAG, "No searchString provided in the intent extras");
        }
    }

    private void fetchGitHubSearch(@NonNull final String searchString) {
        Preconditions.checkNotNull(searchString, "Search String cannot be null.");

        Log.d(TAG, "fetchGitHubSearch(" + searchString + ")");
        if (requestMap.containsKey(searchString.hashCode()) &&
                !requestMap.get(searchString.hashCode()).isUnsubscribed()) {
            Log.d(TAG, "Found an ongoing request for repository " + searchString);
            return;
        }
        final String uri = gitHubRepositorySearchStore.getUriForKey(searchString).toString();
        Subscription subscription = createNetworkObservable(searchString)
                .subscribeOn(Schedulers.computation())
                .map((repositories) -> {
                    final List<Integer> repositoryIds = new ArrayList<>();
                    for (GitHubRepository repository : repositories) {
                        gitHubRepositoryStore.put(repository);
                        repositoryIds.add(repository.getId());
                    }
                    return new GitHubRepositorySearch(searchString, repositoryIds);
                })
                .doOnCompleted(() -> completeRequest(uri))
                .doOnError(doOnError(uri))
                .subscribe(gitHubRepositorySearchStore::put,
                        e -> Log.e(TAG, "Error fetching GitHub repository search for '" + searchString + "'", e));
        requestMap.put(searchString.hashCode(), subscription);
        startRequest(uri);
    }

    @NonNull
    private Observable<List<GitHubRepository>> createNetworkObservable(@NonNull final String searchString) {
        Preconditions.checkNotNull(searchString, "Search String cannot be null.");

        return networkApi.search(Collections.singletonMap("q", searchString));
    }

    @NonNull
    @Override
    public Uri getServiceUri() {
        return GitHubService.REPOSITORY_SEARCH;
    }
}
