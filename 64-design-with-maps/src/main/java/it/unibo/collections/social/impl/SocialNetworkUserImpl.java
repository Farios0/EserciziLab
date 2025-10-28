/**
 *
 */

package it.unibo.collections.social.impl;

import it.unibo.collections.social.api.SocialNetworkUser;
import it.unibo.collections.social.api.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This will be an implementation of
 * {@link SocialNetworkUser}:
 * 1) complete the definition of the methods by following the suggestions
 * included in the comments below.
 *
 * @param <U>
 *            Specific {@link User} type
 */
public final class SocialNetworkUserImpl<U extends User> extends UserImpl implements SocialNetworkUser<U> {

    /*
     *
     * [FIELDS]
     *
     * Define any necessary field
     *
     * In order to save the people followed by a user organized in groups, adopt
     * a generic-type Map:
     *
     * think of what type of keys and values would best suit the requirements
     */

    private Map<String, Set<U>> followed;

    /*
     * [CONSTRUCTORS]
     *
     * 1) Complete the definition of the constructor below, for building a user
     * participating in a social network, with 4 parameters, initializing:
     *
     * - firstName
     * - lastName
     * - username
     * - age and every other necessary field
     */

    
    /**
     * Builds a user participating in a social network.
     *
     * @param name
     *            the user firstname
     * @param surname
     *            the user lastname
     * @param userAge
     *            user's age
     * @param user
     *            alias of the user, i.e. the way a user is identified on an
     *            application
     */
    
     public SocialNetworkUserImpl(final String firstName, final String lastName, final String userName, final int age){
        super(firstName, lastName, userName, age);
        followed = new HashMap<>();
    }

    /*
     * 2) Define a further constructor where the age defaults to -1
     */

    public SocialNetworkUserImpl(final String firstName, final String lastName, final String userName){
        super(firstName, lastName, userName, -1);
        followed = new HashMap<>();
    }

    /*
     * [METHODS]
     *
     * Implements the methods below
     */
    @Override
    public boolean addFollowedUser(final String circle, final U user) {
        Set<U> copy = followed.get(circle);
        if(copy == null) {
            createGroup(circle);
            copy = followed.get(circle);
        }
        return copy.add(user);
    }

    /**
     * this method is responsable of creating a group and the correspondant list to it.
     * It is called when a User wants to add a User to a group that doesn't exists
     * @param circle
     */
    private void createGroup(String circle) {
        followed.put(circle, new HashSet<>());
    }

    /**
     *
     * [NOTE] If no group with groupName exists yet, this implementation must
     * return an empty Collection.
     */
    @Override
    public Collection<U> getFollowedUsersInGroup(final String groupName) {
        Set<U> copy = followed.get(groupName);
        if(copy == null){
            return Collections.emptyList(); 
        }
        return Collections.unmodifiableCollection(copy);
    }

    @Override
    public List<U> getFollowedUsers() {
        List<U> result = new ArrayList<>();
        Collection<Set<U>> total = followed.values();
        for(Set<U> i : total){
            result.addAll(i);
        }
        return result;
    }
}
