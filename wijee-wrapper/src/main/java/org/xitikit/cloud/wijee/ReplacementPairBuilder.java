package org.xitikit.cloud.wijee;

import java.util.LinkedList;
import java.util.List;

/**
 * Copyright Xitikit.org ${year}
 * Builder for use in creating an array of token/replacement pairs (or "key/value")
 * where the token will be replaced by the value of the "replacement" in any given
 * file or String of characters.
 *
 * @author J. Keith Hoopes
 */
public class ReplacementPairBuilder{

    private final List<ReplacementPair> list;

    private ReplacementPairBuilder(){

        list = new LinkedList<>();
    }

    /**
     * Starts the build process by creating an instance of this builder.
     *
     * @return this
     */
    public static ReplacementPairBuilder start(){

        return new ReplacementPairBuilder();
    }

    /**
     * Finish adding values and return an Array
     * of all ReplacementPair objects created.
     *
     * @return ReplacementPair[] containing all added pairs.
     */
    public ReplacementPair[] finish(){

        return list.toArray(new ReplacementPair[list.size()]);
    }

    public ReplacementPairBuilder add(final String token, final String replacement){

        list.add(new ReplacementPair(token, replacement));

        return this;
    }

    public static final class ReplacementPair{

        public final String
            token,
            replacement;

        private ReplacementPair(final String token, final String replacement){

            assert token != null
                && token.startsWith("${")
                && token.endsWith("}")
                && !"".equals(token.substring(0, token.length() - 1).substring(2)) //Inner value cannot be whitespace
                : "'String token' cannot be null or empty. It must also start with '${' and end with '}', with at least one additional non-whitespace character in between.";
            assert replacement != null : "String replacement cannot be null. Empty is allowed, as we may not want to assign any value.";

            this.token = token;
            this.replacement = replacement;
        }
    }
}
