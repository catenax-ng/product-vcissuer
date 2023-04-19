package org.eclipse.tractusx.ssi.lib.model;

import java.net.URI;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import lombok.*;
import org.eclipse.tractusx.ssi.lib.base.MultibaseFactory;

/**
 * E.g. "proof": { "type": "Ed25519Signature2020", "created": "2021-11-13T18:19:39Z",
 * "verificationMethod": "https://example.edu/issuers/14#key-1", "proofPurpose": "assertionMethod",
 * "proofValue": "z58DAdFfa9SkqZMVPxAQpic7ndSayn1PzZs6ZjWp1CktyGesjuTSwRdo
 * WhAfGFCF5bppETSTojQCrfFPP2oumHKtz" }
 */
public class Ed25519Signature2020 extends Proof {

  public static final String ED25519_VERIFICATION_KEY_2018 = "Ed25519Signature2020";
  public static final String TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

  public static final String PROOF_PURPOSE = "proofPurpose";
  public static final String PROOF_VALUE = "proofValue";
  public static final String CREATED = "created";
  public static final String VERIFICATION_METHOD = "verificationMethod";

  public Ed25519Signature2020(Map<String, Object> json) {
    super(json);

    if (!ED25519_VERIFICATION_KEY_2018.equals(json.get(TYPE))) {
      throw new IllegalArgumentException("Invalid Ed25519Signature2020 Type: " + json);
    }

    try {
      // verify getters
      Objects.requireNonNull(this.getProofPurpose());
      Objects.requireNonNull(this.getProofValue());
      Objects.requireNonNull(this.getVerificationMethod());
      Objects.requireNonNull(this.getCreated());
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid Ed25519Signature2020", e);
    }
  }

  public String getProofPurpose() {
    return (String) this.get(PROOF_PURPOSE);
  }

  public MultibaseString getProofValue() {
    return MultibaseFactory.create((String) this.get(PROOF_VALUE));
  }

  public URI getVerificationMethod() {
    return URI.create((String) this.get(VERIFICATION_METHOD));
  }

  public Instant getCreated() {
    return Instant.parse((String) this.get(CREATED));
  }
}